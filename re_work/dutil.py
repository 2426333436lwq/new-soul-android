import struct
from dalvik import iter_code_units
STRING_OPS = (0x1a, 0x1b)
TYPE_OPS = (0x1c, 0x1f, 0x20, 0x22, 0x21)
FIELD_OPS = set(range(0x52, 0x6e))
INVOKE_OPS = set(range(0x6e, 0x73)) | set(range(0x74, 0x79))


def _u16(raw, off):
    if off + 2 > len(raw):
        return None
    return struct.unpack_from('<H', raw, off)[0]


def strings_in_order(d, code):
    out = []
    for off, op, size, raw in iter_code_units(code['insns']):
        v = None
        if op == 0x1a:
            v = _u16(raw, 2)
        elif op == 0x1b and len(raw) >= 6:
            v = struct.unpack_from('<I', raw, 2)[0]
        if v is not None and v < len(d.strings):
            out.append((off, d.strings[v]))
    return out


def calls_in_order(d, code):
    out = []
    for off, op, size, raw in iter_code_units(code['insns']):
        if op in INVOKE_OPS:
            v = _u16(raw, 2)
            if v is not None and v < d.method_ids_size:
                out.append((off, d.method_sig(v), op))
    return out


def types_in_order(d, code):
    out = []
    for off, op, size, raw in iter_code_units(code['insns']):
        if op in TYPE_OPS:
            v = _u16(raw, 2)
            if v is not None and v < d.type_ids_size:
                out.append((off, d.type_of(v)))
    return out


def fields_in_order(d, code):
    out = []
    for off, op, size, raw in iter_code_units(code['insns']):
        if op in FIELD_OPS:
            v = _u16(raw, 2)
            if v is not None and v < d.field_ids_size:
                f = d.fields[v]
                out.append((off, f['class'] + '->' + f['name'] + ':' + f['type']))
    return out