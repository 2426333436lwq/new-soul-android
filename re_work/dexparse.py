"""Minimal but complete DEX (Dalvik Executable) parser.

Recovers header, string/type/proto/field/method/class tables and code items.
Written against the public DEX format spec (Android dex-format).
"""
import struct, sys, json, io

class DEX:
    def __init__(self, data: bytes):
        self.data = data
        self._parse_header()
        self._parse_strings()
        self._parse_type_ids()
        self._parse_proto_ids()
        self._parse_field_ids()
        self._parse_method_ids()
        self._parse_class_defs()

    # ---------- helpers ----------
    def _u32(self, off):
        return struct.unpack_from('<I', self.data, off)[0]

    def _u16(self, off):
        return struct.unpack_from('<H', self.data, off)[0]

    def _uleb128(self, off):
        result = 0
        shift = 0
        while True:
            b = self.data[off]
            off += 1
            result |= (b & 0x7F) << shift
            if not (b & 0x80):
                break
            shift += 7
        return result, off

    def _uleb128p1(self, off):
        v, off = self._uleb128(off)
        return v - 1, off

    def _sleb128(self, off):
        result = 0
        shift = 0
        while True:
            b = self.data[off]
            off += 1
            result |= (b & 0x7F) << shift
            shift += 7
            if not (b & 0x80):
                if b & 0x40:
                    result |= -(1 << shift)
                break
        return result, off

    # ---------- header ----------
    def _parse_header(self):
        magic = self.data[0:8]
        if magic not in (b'dex\n035\x00', b'dex\n036\x00', b'dex\n037\x00',
                         b'dex\n038\x00', b'dex\n039\x00', b'dex\n040\x00'):
            raise ValueError(f'not a dex: {magic!r}')
        self.version = magic[4:7].decode()
        (self.checksum, self.signature_sha1, self.file_size, self.header_size,
         self.endian_tag, self.link_size, self.link_off, self.map_off,
         self.string_ids_size, self.string_ids_off,
         self.type_ids_size, self.type_ids_off,
         self.proto_ids_size, self.proto_ids_off,
         self.field_ids_size, self.field_ids_off,
         self.method_ids_size, self.method_ids_off,
         self.class_defs_size, self.class_defs_off,
         self.data_size, self.data_off) = struct.unpack_from('<I20s20I', self.data, 8)
        assert self.header_size == 0x70, self.header_size

    # ---------- strings ----------
    @staticmethod
    def _decode_mutf8(raw: bytes) -> str:
        """Decode DEX MUTF-8: UTF-16 code units encoded in a modified UTF-8.

        Differences from UTF-8: NUL is encoded as 0xC0 0x80, and supplementary
        characters are encoded as surrogate pairs (CESU-8 style), not as 4-byte
        sequences. Decode to UTF-16 units then combine surrogate pairs.
        """
        units = []
        i = 0
        n = len(raw)
        while i < n:
            b = raw[i]
            if b < 0x80:
                units.append(b)
                i += 1
            elif (b & 0xE0) == 0xC0:
                if i + 1 >= n:
                    break
                units.append(((b & 0x1F) << 6) | (raw[i + 1] & 0x3F))
                i += 2
            elif (b & 0xF0) == 0xE0:
                if i + 2 >= n:
                    break
                units.append(((b & 0x0F) << 12) | ((raw[i + 1] & 0x3F) << 6)
                             | (raw[i + 2] & 0x3F))
                i += 3
            elif (b & 0xF8) == 0xF0:
                # non-standard 4-byte form; decode to a supplementary code point
                if i + 3 >= n:
                    break
                cp = ((b & 0x07) << 18) | ((raw[i + 1] & 0x3F) << 12) \
                     | ((raw[i + 2] & 0x3F) << 6) | (raw[i + 3] & 0x3F)
                cp -= 0x10000
                units.append(0xD800 + (cp >> 10))
                units.append(0xDC00 + (cp & 0x3FF))
                i += 4
            else:
                units.append(0xFFFD); i += 1
        # combine surrogate pairs
        out = []
        k = 0
        while k < len(units):
            u = units[k]
            if 0xD800 <= u <= 0xDBFF and k + 1 < len(units) and 0xDC00 <= units[k + 1] <= 0xDFFF:
                cp = 0x10000 + ((u - 0xD800) << 10) + (units[k + 1] - 0xDC00)
                out.append(chr(cp))
                k += 2
                continue
            if 0xD800 <= u <= 0xDFFF:
                out.append('\ufffd')
            else:
                out.append(chr(u))
            k += 1
        return ''.join(out)

    def _parse_strings(self):
        self.strings = []
        for i in range(self.string_ids_size):
            off = self._u32(self.string_ids_off + 4 * i)
            n, p = self._uleb128(off)          # n = UTF-16 code-unit count
            # read up to the NUL terminator, but trust n when available
            end = p + n
            raw = self.data[p:end]
            self.strings.append(self._decode_mutf8(raw))
        self._string_index = {s: i for i, s in enumerate(self.strings)}

    # ---------- types ----------
    def _parse_type_ids(self):
        self.types = [self.strings[self._u32(self.type_ids_off + 4 * i)]
                      for i in range(self.type_ids_size)]

    def type_of(self, idx):
        if idx == 0xFFFFFFFF or idx < 0 or idx >= len(self.types):
            return None
        return self.types[idx]

    # ---------- protos ----------
    def _parse_proto_ids(self):
        self.protos = []
        for i in range(self.proto_ids_size):
            off = self.proto_ids_off + 12 * i
            shorty_idx, return_type_idx, params_off = struct.unpack_from('<III', self.data, off)
            params = []
            if params_off:
                size = self._u32(params_off)
                for k in range(size):
                    params.append(self.type_of(self._u16(params_off + 4 + 2 * k)))
            self.protos.append({
                'shorty': self.strings[shorty_idx],
                'return': self.type_of(return_type_idx),
                'params': params,
            })

    # ---------- fields ----------
    def _parse_field_ids(self):
        self.fields = []
        for i in range(self.field_ids_size):
            off = self.field_ids_off + 8 * i
            class_idx, type_idx, name_idx = struct.unpack_from('<HHI', self.data, off)
            self.fields.append({
                'class': self.types[class_idx],
                'type': self.types[type_idx],
                'name': self.strings[name_idx],
            })

    # ---------- methods ----------
    def _parse_method_ids(self):
        self.methods = []
        for i in range(self.method_ids_size):
            off = self.method_ids_off + 8 * i
            class_idx, proto_idx, name_idx = struct.unpack_from('<HHI', self.data, off)
            p = self.protos[proto_idx]
            self.methods.append({
                'class': self.types[class_idx],
                'name': self.strings[name_idx],
                'proto': p,
                'params': p['params'],
                'return': p['return'],
                'shorty': p['shorty'],
            })

    def method_sig(self, i):
        m = self.methods[i]
        return f"{m['class']}->{m['name']}({''.join(m['params'])}){m['return']}"

    # ---------- classes ----------
    def _parse_class_defs(self):
        self.classes = []
        for i in range(self.class_defs_size):
            off = self.class_defs_off + 32 * i
            (class_idx, access_flags, superclass_idx, interfaces_off,
             source_file_idx, annotations_off, class_data_off,
             static_values_off) = struct.unpack_from('<IIIIIIII', self.data, off)
            cd = {
                'name': self.types[class_idx],
                'access': access_flags,
                'super': self.type_of(superclass_idx),
                'source_file': self.strings[source_file_idx] if source_file_idx != 0xFFFFFFFF else None,
                'interfaces': [],
                'class_data_off': class_data_off,
                'annotations_off': annotations_off,
                'static_values_off': static_values_off,
            }
            if interfaces_off:
                n = self._u32(interfaces_off)
                cd['interfaces'] = [self.type_of(self._u16(interfaces_off + 4 + 2 * k))
                                    for k in range(n)]
            self.classes.append(cd)

    # ---------- class_data (methods/fields per class) ----------
    def parse_class_data(self, off):
        """Returns dict with direct/virtual methods and static/instance fields."""
        out = {'static_fields': [], 'instance_fields': [],
               'direct_methods': [], 'virtual_methods': []}
        if not off:
            return out
        p = off
        static_fields_size, p = self._uleb128(p)
        instance_fields_size, p = self._uleb128(p)
        direct_methods_size, p = self._uleb128(p)
        virtual_methods_size, p = self._uleb128(p)

        def read_fields(count, p):
            res = []
            idx = 0
            for _ in range(count):
                diff, p = self._uleb128(p)
                acc, p = self._uleb128(p)
                idx += diff
                f = self.fields[idx]
                res.append({**f, 'access': acc, 'index': idx})
            return res, p

        def read_methods(count, p):
            res = []
            idx = 0
            for _ in range(count):
                diff, p = self._uleb128(p)
                acc, p = self._uleb128(p)
                code_off, p = self._uleb128(p)
                idx += diff
                m = self.methods[idx]
                res.append({'index': idx, 'access': acc, 'code_off': code_off,
                            'name': m['name'], 'class': m['class'],
                            'params': m['params'], 'return': m['return'],
                            'sig': self.method_sig(idx)})
            return res, p

        out['static_fields'], p = read_fields(static_fields_size, p)
        out['instance_fields'], p = read_fields(instance_fields_size, p)
        out['direct_methods'], p = read_methods(direct_methods_size, p)
        out['virtual_methods'], p = read_methods(virtual_methods_size, p)
        return out

    # ---------- code item -> instructions ----------
    def parse_code_item(self, off):
        if not off:
            return None
        registers_size, ins_size, outs_size, tries_size = struct.unpack_from('<HHHH', self.data, off)
        debug_info_off, insns_size = struct.unpack_from('<II', self.data, off + 8)
        insns_off = off + 16
        return {
            'registers_size': registers_size,
            'ins_size': ins_size,
            'outs_size': outs_size,
            'tries_size': tries_size,
            'debug_info_off': debug_info_off,
            'insns_size': insns_size,   # in 16-bit code units
            'insns_off': insns_off,
            'insns': self.data[insns_off: insns_off + insns_size * 2],
        }

    # ---------- annotation parsing (for @XposedHook etc.) ----------
    def parse_encoded_value(self, off):
        """Return (value, new_off) for an encoded_value at off."""
        tag = self.data[off]; off += 1
        vtype = tag & 0x1F
        # argument is high 3 bits, stored in reverse-nibble form
        arg = (tag >> 5) & 0x7
        size = arg + 1

        def read_int(sz, signed=True):
            nonlocal off
            val = 0
            for k in range(sz):
                val |= self.data[off + k] << (8 * k)
            if signed and (self.data[off + sz - 1] & 0x80):
                val -= (1 << (8 * sz))
            off += sz
            return val

        if vtype == 0x00:      # VALUE_BYTE
            v = read_int(1, signed=False); return v, off
        if vtype == 0x02:      # VALUE_SHORT
            v = read_int(size); return v, off
        if vtype == 0x03:      # VALUE_CHAR
            v = read_int(size, signed=False); return chr(v), off
        if vtype == 0x04:      # VALUE_INT
            v = read_int(size); return v, off
        if vtype == 0x06:      # VALUE_LONG
            v = read_int(size); return v, off
        if vtype == 0x10:      # VALUE_METHOD_TYPE (proto idx)
            v = read_int(size, signed=False); return ('proto', v), off
        if vtype == 0x11:      # VALUE_METHOD_HANDLE
            v = read_int(size, signed=False); return ('method_handle', v), off
        if vtype == 0x15:      # VALUE_STRING
            v = read_int(size, signed=False)
            return self.strings[v], off
        if vtype == 0x16:      # VALUE_TYPE
            v = read_int(size, signed=False)
            return self.type_of(v), off
        if vtype == 0x17:      # VALUE_FIELD
            v = read_int(size, signed=False)
            return ('field', self.fields[v]), off
        if vtype == 0x18:      # VALUE_METHOD
            v = read_int(size, signed=False)
            return ('method', self.method_sig(v)), off
        if vtype == 0x19:      # VALUE_ENUM
            v = read_int(size, signed=False)
            return ('enum', self.fields[v]), off
        if vtype == 0x1a:      # VALUE_ARRAY
            n, off = self._uleb128(off)
            arr = []
            for _ in range(n):
                item, off = self.parse_encoded_value(off)
                arr.append(item)
            return arr, off
        if vtype == 0x1b:      # VALUE_ANNOTATION
            return self.parse_encoded_annotation(off)
        if vtype == 0x1c:      # VALUE_NULL
            return None, off
        if vtype == 0x1d:      # VALUE_BOOLEAN
            return bool(arg), off
        if vtype == 0x1e:      # VALUE_NULL
            return None, off
        if vtype == 0x1f:      # VALUE_NULL
            return None, off
        raise ValueError(f'unknown encoded value type 0x{vtype:02x} at {off-1}')

    def parse_encoded_annotation(self, off):
        type_idx, off = self._uleb128(off)
        size, off = self._uleb128(off)
        elements = {}
        for _ in range(size):
            name_idx, off = self._uleb128(off)
            name = self.strings[name_idx]
            val, off = self.parse_encoded_value(off)
            elements[name] = val
        return {'type': self.type_of(type_idx), 'elements': elements}, off

    def annotation_set(self, off):
        """annotation_set_item -> list of annotation dicts"""
        if not off:
            return []
        size = self._u32(off)
        res = []
        for i in range(size):
            a_off = self._u32(off + 4 + 4 * i)
            # annotation_item: visibility byte then encoded_annotation
            vis = self.data[a_off]
            ann, _ = self.parse_encoded_annotation(a_off + 1)
            ann['visibility'] = vis
            res.append(ann)
        return res

    def annotation_set_ref_list(self, off):
        if not off:
            return []
        size = self._u32(off)
        out = []
        for i in range(size):
            a_off = self._u32(off + 4 + 4 * i)
            out.append(self.annotation_set(a_off))
        return out

    def annotations_directory(self, off):
        """class/method/field annotations."""
        if not off:
            return None
        class_ann_off, fields_size, methods_size, params_size = struct.unpack_from('<IIII', self.data, off)
        out = {'class': self.annotation_set(class_ann_off), 'fields': [], 'methods': [], 'params': []}
        p = off + 16
        for _ in range(fields_size):
            fidx, a_off = struct.unpack_from('<II', self.data, p); p += 8
            out['fields'].append({'field_idx': fidx, 'annotations': self.annotation_set(a_off)})
        for _ in range(methods_size):
            midx, a_off = struct.unpack_from('<II', self.data, p); p += 8
            out['methods'].append({'method_idx': midx, 'annotations': self.annotation_set(a_off)})
        for _ in range(params_size):
            midx, a_off = struct.unpack_from('<II', self.data, p); p += 8
            out['params'].append({'method_idx': midx, 'annotations': self.annotation_set_ref_list(a_off)})
        return out


if __name__ == '__main__':
    path = sys.argv[1]
    with open(path, 'rb') as f:
        d = DEX(f.read())
    print(f"DEX version {d.version}")
    print(f"file_size={d.file_size} header=0x{d.header_size:x} endian=0x{d.endian_tag:x}")
    print(f"strings={d.string_ids_size} types={d.type_ids_size} protos={d.proto_ids_size} "
          f"fields={d.field_ids_size} methods={d.method_ids_size} classes={d.class_defs_size}")
    print()
    print("=== CLASSES ===")
    for c in d.classes:
        cd = d.parse_class_data(c['class_data_off'])
        print(f"\n{c['name']}  (super={c['super']}, src={c['source_file']}, access=0x{c['access']:x})")
        if c['interfaces']:
            print(f"   implements: {', '.join(c['interfaces'])}")
        for f in cd['static_fields']:
            print(f"   [static] {f['type']} {f['name']}")
        for f in cd['instance_fields']:
            print(f"   [field ] {f['type']} {f['name']}")
        for m in cd['direct_methods'] + cd['virtual_methods']:
            print(f"   [method] {m['name']}({', '.join(m['params'])}) -> {m['return']}   code=0x{m['code_off']:x}")
