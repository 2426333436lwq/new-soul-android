"""Decode binary AndroidManifest.xml (AXML) from the APK."""
import struct, sys

def decode_axml(data: bytes):
    # header
    magic, size = struct.unpack_from('<II', data, 0)
    assert magic == 0x00080003, hex(magic)

    strings = []
    res_ids = []

    def parse_string_pool(off):
        t, hdr_size, chunk_size = struct.unpack_from('<HHI', data, off)
        string_count, style_count, flags, strings_start, styles_start = \
            struct.unpack_from('<IIIII', data, off + 8)
        is_utf8 = bool(flags & (1 << 8))
        offsets = [struct.unpack_from('<I', data, off + 28 + 4 * i)[0]
                   for i in range(string_count)]
        out = []
        for o in offsets:
            p = off + strings_start + o
            if is_utf8:
                # length in chars (1 or 2 bytes), then in bytes, then data
                n = data[p]; p += 1
                if n & 0x80:
                    n = ((n & 0x7F) << 8) | data[p]; p += 1
                nb = data[p]; p += 1
                if nb & 0x80:
                    nb = ((nb & 0x7F) << 8) | data[p]; p += 1
                out.append(data[p:p + nb].decode('utf-8', 'replace'))
            else:
                n = struct.unpack_from('<H', data, p)[0]; p += 2
                if n & 0x8000:
                    n = ((n & 0x7FFF) << 16) | struct.unpack_from('<H', data, p)[0]; p += 2
                raw = data[p:p + n * 2]
                out.append(raw.decode('utf-16-le', 'replace'))
        return out, hdr_size, chunk_size

    pos = 8
    xml_nodes = []
    while pos < len(data):
        ctype, hdr_size, chunk_size = struct.unpack_from('<HHI', data, pos)
        if ctype == 0x0001:      # string pool
            strings, hs, cs = parse_string_pool(pos)
            pos += cs
            continue
        if ctype == 0x0180:      # resource map
            n = (chunk_size - hdr_size) // 4
            res_ids = [struct.unpack_from('<I', data, pos + hdr_size + 4 * i)[0]
                       for i in range(n)]
            pos += chunk_size
            continue
        if ctype == 0x0102:      # START_ELEMENT
            # ResXMLTree_node: type(2) hdrSize(2) size(4) lineNumber(4) comment(4) = 16 bytes
            # then ResXMLTree_attrExt: ns(4) name(4) attrStart(2) attrSize(2) attrCount(2) idIndex(2) classIndex(2) styleIndex(2)
            name_raw = struct.unpack_from('<i', data, pos + 20)[0]
            name_idx = name_raw
            attr_start, attr_size, attr_count = struct.unpack_from('<HHH', data, pos + 24)
            attrs = []
            for i in range(attr_count):
                a_off = pos + 16 + attr_start + i * attr_size
                # ResXMLTree_attribute: ns(4) name(4) rawValue(4)
                #   then Res_value: size(2) res0(1) dataType(1) data(4)
                a_ns, a_name, a_rawval = struct.unpack_from('<iii', data, a_off)
                v_size, v_res0, v_type = struct.unpack_from('<HBB', data, a_off + 12)
                v_data = struct.unpack_from('<I', data, a_off + 16)[0]
                if v_type == 0x03:      # TYPE_STRING
                    val = strings[v_data] if v_data < len(strings) else f'<str{v_data}>'
                elif v_type == 0x10:    # TYPE_INT_DEC
                    val = str(struct.unpack_from('<i', data, a_off + 16)[0])
                elif v_type == 0x11:    # TYPE_INT_HEX
                    val = hex(v_data)
                elif v_type == 0x12:    # TYPE_INT_BOOLEAN
                    val = 'true' if v_data else 'false'
                elif v_type == 0x01:    # TYPE_REFERENCE
                    val = f'@{hex(v_data)}'
                elif v_type == 0x02:    # TYPE_ATTRIBUTE
                    val = f'?{hex(v_data)}'
                elif v_type == 0x04:    # TYPE_FLOAT
                    val = str(struct.unpack_from('<f', data, a_off + 16)[0])
                elif v_type == 0x05:    # TYPE_DIMENSION
                    val = f'{v_data}dim'
                else:
                    val = f'<type{v_type:#x}:{v_data:#x}>'
                aname = strings[a_name] if a_name < len(strings) else '?'
                attrs.append((aname, val))
            xml_nodes.append(('start', strings[name_idx] if name_idx < len(strings) else '?', attrs))
            pos += chunk_size
            continue
        if ctype == 0x0103:      # END_ELEMENT
            name_idx = struct.unpack_from('<i', data, pos + 20)[0]
            xml_nodes.append(('end', strings[name_idx] if name_idx < len(strings) else '?', []))
            pos += chunk_size
            continue
        if chunk_size == 0:
            break
        pos += chunk_size

    # render
    out = []
    depth = 0
    for kind, name, attrs in xml_nodes:
        if kind == 'start':
            pad = '  ' * depth
            if attrs:
                out.append(f'{pad}<{name}')
                for k, v in attrs:
                    out.append(f'{pad}    {k}="{v}"')
                out.append(f'{pad}>')
            else:
                out.append(f'{pad}<{name}>')
            depth += 1
        else:
            depth = max(0, depth - 1)
            out.append(f'{"  " * depth}</{name}>')
    return '\n'.join(out), strings

if __name__ == '__main__':
    path = sys.argv[1]
    with open(path, 'rb') as f:
        data = f.read()
    xml, strings = decode_axml(data)
    print(xml)
