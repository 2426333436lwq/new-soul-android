"""Dalvik disassembler (ads work) - name-spaced to avoid collisions."""
import struct
from dalvik import iter_code_units


def u16(raw, o): return struct.unpack_from("<H", raw, o)[0]
def s16(raw, o): return struct.unpack_from("<h", raw, o)[0]
def u8(raw, o): return raw[o]


def s_idx(d, raw, o): return d.strings[u16(raw, o)]
def t_idx(d, raw, o): return d.type_of(u16(raw, o))
def m_idx(d, raw, o): return d.method_sig(u16(raw, o))


def f_idx(d, raw, o):
    i = u16(raw, o)
    if i >= len(d.fields):
        return "?field%d" % i
    f = d.fields[i]
    return "%s->%s:%s" % (f["class"], f["name"], f["type"])


def _inv35c(raw):
    """Return (count, register list) for 35c/3rc-style operand."""
    cnt = u8(raw, 1) >> 4
    nib = [u8(raw, 4) & 0xf, (u8(raw, 4) >> 4) & 0xf, u8(raw, 5) & 0xf,
           (u8(raw, 5) >> 4) & 0xf, u8(raw, 1) & 0xf]
    return cnt, nib[:cnt]


_NM_2ADDR = {0xb0: "add-int/2addr", 0xb1: "sub-int/2addr", 0xb2: "mul-int/2addr",
             0xb3: "div-int/2addr", 0xb4: "rem-int/2addr", 0xb5: "and-int/2addr",
             0xb6: "or-int/2addr", 0xb7: "xor-int/2addr", 0xb8: "shl-int/2addr",
             0xb9: "shr-int/2addr", 0xba: "ushr-int/2addr"}


def decode(d, op, raw, size):
    try:
        if op == 0x00:
            if raw[1] == 0x01: return "packed-switch-payload"
            if raw[1] == 0x02: return "sparse-switch-payload"
            if raw[1] == 0x03: return "fill-array-data-payload"
            return "nop"
        if op == 0x01: return "move v%d, v%d" % (u8(raw, 1) & 0xf, (u8(raw, 1) >> 4) & 0xf)
        if op == 0x0a: return "move-result v%d" % u8(raw, 1)
        if op == 0x0b: return "move-result-wide v%d" % u8(raw, 1)
        if op == 0x0c: return "move-result-object v%d" % u8(raw, 1)
        if op == 0x0d: return "move-exception v%d" % u8(raw, 1)
        if op == 0x0e: return "return-void"
        if op == 0x0f: return "return v%d" % u8(raw, 1)
        if op == 0x10: return "return-wide v%d" % u8(raw, 1)
        if op == 0x11: return "return-object v%d" % u8(raw, 1)
        if op == 0x12:
            v = u8(raw, 1) >> 4
            if v > 7: v -= 16
            return "const/4 v%d, %d" % (u8(raw, 1) & 0xf, v)
        if op == 0x13: return "const/16 v%d, %d" % (u8(raw, 1), s16(raw, 2))
        if op == 0x14: return "const v%d, %d" % (u8(raw, 1), struct.unpack_from("<i", raw, 2)[0])
        if op == 0x15: return "const/high16 v%d, 0x%x" % (u8(raw, 1), u16(raw, 2))
        if op == 0x1a: return "const-string v%d, %r" % (u8(raw, 1), s_idx(d, raw, 2))
        if op == 0x1b:
            return "const-string/jumbo v%d, %r" % (u8(raw, 1), d.strings[struct.unpack_from("<I", raw, 2)[0]])
        if op == 0x1c: return "const-class v%d, %s" % (u8(raw, 1), t_idx(d, raw, 2))
        if op == 0x1d: return "monitor-enter v%d" % u8(raw, 1)
        if op == 0x1e: return "monitor-exit v%d" % u8(raw, 1)
        if op == 0x1f: return "check-cast v%d, %s" % (u8(raw, 1), t_idx(d, raw, 2))
        if op == 0x20: return "instance-of v%d, v%d, %s" % (u8(raw, 1) & 0xf, u8(raw, 2), t_idx(d, raw, 2))
        if op == 0x21: return "array-length v%d, v%d" % (u8(raw, 1), u8(raw, 2))
        if op == 0x22: return "new-instance v%d, %s" % (u8(raw, 1), t_idx(d, raw, 2))
        if op == 0x23: return "new-array v%d, v%d, %s" % (u8(raw, 1), u8(raw, 2), t_idx(d, raw, 2))
        if op in (0x24, 0x25):
            cnt, nib = _inv35c(raw)
            return "filled-new-array {%s}, %s   [cnt=%d]" % (
                ", ".join("v%d" % x for x in nib), t_idx(d, raw, 2), cnt)
        if op == 0x26: return "fill-array-data v%d, +%d" % (u8(raw, 1), struct.unpack_from("<i", raw, 2)[0])
        if op == 0x27: return "throw v%d" % u8(raw, 1)
        if op == 0x28: return "goto %+d" % (struct.unpack_from("<b", raw, 1)[0])
        if op == 0x29: return "goto/16 %+d" % s16(raw, 2)
        if op == 0x2a: return "goto/32 %+d" % struct.unpack_from("<i", raw, 2)[0]
        if op in (0x2b, 0x2c): return "switch v%d, %+d" % (u8(raw, 1), struct.unpack_from("<i", raw, 2)[0])
        if op in (0x2d, 0x2e, 0x2f, 0x30, 0x31):
            nm = {0x2d: "cmpl-float", 0x2e: "cmpg-float", 0x2f: "cmpl-double",
                  0x30: "cmpg-double", 0x31: "cmp-long"}[op]
            return "%s v%d, v%d, v%d" % (nm, u8(raw, 1), u8(raw, 2), u8(raw, 3))
        if op in range(0x32, 0x38):
            nm = {0x32: "if-eq", 0x33: "if-ne", 0x34: "if-lt", 0x35: "if-ge",
                  0x36: "if-gt", 0x37: "if-le"}[op]
            return "%s v%d, v%d, %+d" % (nm, u8(raw, 1), u8(raw, 2), s16(raw, 2))
        if op in range(0x38, 0x3e):
            nm = {0x38: "if-eqz", 0x39: "if-nez", 0x3a: "if-ltz", 0x3b: "if-gez",
                  0x3c: "if-gtz", 0x3d: "if-lez"}[op]
            return "%s v%d, %+d" % (nm, u8(raw, 1), s16(raw, 2))
        if op == 0x3e: return "unused-3e"
        if op in range(0x44, 0x52):
            nm = {0x44: "aget", 0x45: "aget-wide", 0x46: "aget-object", 0x47: "aget-boolean",
                  0x48: "aget-byte", 0x49: "aget-char", 0x4a: "aget-short", 0x4b: "aput",
                  0x4c: "aput-wide", 0x4d: "aput-object", 0x4e: "aput-boolean",
                  0x4f: "aput-byte", 0x50: "aput-char", 0x51: "aput-short"}[op]
            return "%s v%d, v%d, v%d" % (nm, u8(raw, 1), u8(raw, 2), u8(raw, 3))
        if op in range(0x52, 0x5b):
            nm = {0x52: "iget", 0x53: "iget-wide", 0x54: "iget-object", 0x55: "iget-boolean",
                  0x56: "iget-byte", 0x57: "iget-char", 0x58: "iget-short", 0x59: "iput",
                  0x5a: "iput-wide"}[op]
            return "%s v%d, v%d, %s" % (nm, u8(raw, 1), u8(raw, 2), f_idx(d, raw, 2))
        if op in range(0x5b, 0x60):
            nm = {0x5b: "iput-object", 0x5c: "iput-boolean", 0x5d: "iput-byte",
                  0x5e: "iput-char", 0x5f: "iput-short"}[op]
            return "%s v%d, v%d, %s" % (nm, u8(raw, 1), u8(raw, 2), f_idx(d, raw, 2))
        if op in range(0x60, 0x6e):
            nm = {0x60: "sget", 0x61: "sget-wide", 0x62: "sget-object", 0x63: "sget-boolean",
                  0x64: "sget-byte", 0x65: "sget-char", 0x66: "sget-short", 0x67: "sput",
                  0x68: "sput-wide", 0x69: "sput-object", 0x6a: "sput-boolean",
                  0x6b: "sput-byte", 0x6c: "sput-char", 0x6d: "sput-short"}[op]
            return "%s v%d, %s" % (nm, u8(raw, 1), f_idx(d, raw, 2))
        if op in range(0x6e, 0x73):
            nm = {0x6e: "invoke-virtual", 0x6f: "invoke-super", 0x70: "invoke-direct",
                  0x71: "invoke-static", 0x72: "invoke-interface"}[op]
            cnt, nib = _inv35c(raw)
            return "%s {%s}, %s" % (nm, ", ".join("v%d" % x for x in nib), m_idx(d, raw, 2))
        if op in range(0x74, 0x79):
            nm = {0x74: "invoke-virtual/range", 0x75: "invoke-super/range",
                  0x76: "invoke-direct/range", 0x77: "invoke-static/range",
                  0x78: "invoke-interface/range"}[op]
            st = u16(raw, 4); cnt = u8(raw, 1)
            return "%s {v%d..v%d}, %s" % (nm, st, st + cnt - 1, m_idx(d, raw, 2))
        if op in range(0x7b, 0x82):
            nm = {0x7b: "neg-int", 0x7c: "not-int", 0x7d: "neg-long", 0x7e: "not-long",
                  0x7f: "neg-float", 0x80: "neg-double", 0x81: "int-to-long"}[op]
            return "%s v%d, v%d" % (nm, u8(raw, 1), u8(raw, 2))
        if op in range(0x82, 0x90):
            nm = {0x82: "int-to-float", 0x83: "int-to-double", 0x84: "long-to-int",
                  0x85: "long-to-float", 0x86: "long-to-double", 0x87: "float-to-int",
                  0x88: "float-to-long", 0x89: "float-to-double", 0x8a: "double-to-int",
                  0x8b: "double-to-long", 0x8c: "double-to-float", 0x8d: "int-to-byte",
                  0x8e: "int-to-char", 0x8f: "int-to-short"}[op]
            return "%s v%d, v%d" % (nm, u8(raw, 1), u8(raw, 2))
        if op in range(0x90, 0xb0):
            nm = {0x90: "add-int", 0x91: "sub-int", 0x92: "mul-int", 0x93: "div-int",
                  0x94: "rem-int", 0x95: "and-int", 0x96: "or-int", 0x97: "xor-int",
                  0x98: "shl-int", 0x99: "shr-int", 0x9a: "ushr-int", 0x9b: "add-long",
                  0x9c: "sub-long", 0x9d: "mul-long", 0x9e: "div-long", 0x9f: "rem-long",
                  0xa0: "and-long", 0xa1: "or-long", 0xa2: "xor-long", 0xa3: "shl-long",
                  0xa4: "shr-long", 0xa5: "ushr-long", 0xa6: "add-float", 0xa7: "sub-float",
                  0xa8: "mul-float", 0xa9: "div-float", 0xaa: "rem-float", 0xab: "add-double",
                  0xac: "sub-double", 0xad: "mul-double", 0xae: "div-double", 0xaf: "rem-double"}[op]
            return "%s v%d, v%d, v%d" % (nm, u8(raw, 1), u8(raw, 2), u8(raw, 3))
        if op in _NM_2ADDR:
            return "%s v%d, v%d" % (_NM_2ADDR[op], u8(raw, 1), u8(raw, 2))
        if op in range(0xb0, 0xd0):
            return "op-2addr 0x%02x v%d, v%d" % (op, u8(raw, 1), u8(raw, 2))
        if op in range(0xd0, 0xd8):
            nm = {0xd0: "add-int/lit16", 0xd1: "rsub-int", 0xd2: "mul-int/lit16",
                  0xd3: "div-int/lit16", 0xd4: "rem-int/lit16", 0xd5: "and-int/lit16",
                  0xd6: "or-int/lit16", 0xd7: "xor-int/lit16"}[op]
            return "%s v%d, v%d, %d" % (nm, u8(raw, 1), u8(raw, 2), s16(raw, 2))
        if op in range(0xd8, 0xe3):
            nm = {0xd8: "add-int/lit8", 0xd9: "rsub-int/lit8", 0xda: "mul-int/lit8",
                  0xdb: "div-int/lit8", 0xdc: "rem-int/lit8", 0xdd: "and-int/lit8",
                  0xde: "or-int/lit8", 0xdf: "xor-int/lit8", 0xe0: "shl-int/lit8",
                  0xe1: "shr-int/lit8", 0xe2: "ushr-int/lit8"}[op]
            b = raw[3]
            if b > 127: b -= 256
            return "%s v%d, v%d, %d" % (nm, u8(raw, 1), u8(raw, 2), b)
        if op == 0xe3: return "op 0xe3 raw=%s" % raw.hex()
        if op in range(0xf0, 0xfa): return "invoke-polymorphic 0x%02x raw=%s" % (op, raw.hex())
        if op in range(0xfa, 0x100): return "invoke-polymorphic/range 0x%02x raw=%s" % (op, raw.hex())
        return "op 0x%02x raw=%s" % (op, raw.hex())
    except Exception as e:
        return "op 0x%02x ERR %s raw=%s" % (op, e, raw.hex())


def disasm(d, code):
    out = ["; registers=%d ins=%d outs=%d units=%d" % (code["registers_size"], code["ins_size"],
                                                       code["outs_size"], code["insns_size"])]
    for off, op, size, raw in iter_code_units(code["insns"]):
        out.append("  %04x: %s" % (off, decode(d, op, raw, size)))
    return "\n".join(out)
