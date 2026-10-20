"""Correct Dalvik instruction size table + full xref extraction.

The DEX instruction format is fixed by opcode; sizes below follow the
official Dalvik bytecode table (instruction-formats).
"""
import struct

# Instruction sizes in 16-bit code units, by opcode.
# Authoritative for the Dalvik bytecode set (instruction-formats).
_fmt = {}
for _op in range(0x100):
    _fmt[_op] = 1  # default; every real opcode below is overridden
_fmt.update({
    # 10x nop, 12x move, 11n/11x/10t
    0x00:1, 0x01:1, 0x02:1, 0x03:1, 0x04:1, 0x05:1, 0x06:1, 0x07:1, 0x08:1, 0x09:1,
    0x0a:1, 0x0b:1, 0x0c:1, 0x0d:1, 0x0e:1, 0x0f:1, 0x10:1, 0x11:1,
    # 11n, 21s, 31i, 21h, 21c, 23x, 22b, 22s, 22c, 22t, 21t
    0x12:1, 0x13:1, 0x14:2, 0x15:2, 0x16:2, 0x17:2, 0x18:2, 0x19:2,
    0x1a:2,                      # const-string    21c  <-- 2 units
    0x1b:3,                      # const-string/jumbo 31c
    0x1c:2,                      # const-class     21c
    0x1d:1, 0x1e:1,
    0x1f:2,                      # check-cast      21c
    0x20:2,                      # instance-of     22c
    0x21:1,                      # array-length    12x
    0x22:2,                      # new-instance    21c
    0x23:2,                      # new-array       22c
    0x24:3,                      # filled-new-array 35c
    0x25:3,                      # filled-new-array/range 3rc
    0x26:3,                      # fill-array-data 31t
    0x27:1,                      # throw           11x
    0x28:1,                      # goto            10t
    0x29:2,                      # goto/16         20t
    0x2a:3,                      # goto/32         30t
    # 31t switch, 31i cmp, 22c etc.
    0x2b:3, 0x2c:3,
    0x2d:2, 0x2e:2, 0x2f:2, 0x30:2, 0x31:2, 0x32:2, 0x33:2, 0x34:2,
    0x35:3, 0x36:3, 0x37:3, 0x38:3,
    # cmp/cmpg/cmpl 23x
    0x39:2, 0x3a:2, 0x3b:2, 0x3c:2, 0x3d:2,
    0x3e:1,
    0x3f:2, 0x40:2, 0x41:2, 0x42:2, 0x43:2, 0x44:2,
})
# 0x45..0x51: 23x (2 units) ; 0x52..0x5f: 22c/21c (2 units)
for _op in range(0x45, 0x60):
    _fmt[_op] = 2
# 0x60..0x6d: 21c/22c/21s (2 units) ; 0x6e..0x72: 35c (3) ; 0x74..0x78: 3rc (3)
for _op in range(0x60, 0x6e):
    _fmt[_op] = 2
for _op in range(0x6e, 0x73):
    _fmt[_op] = 3
_fmt[0x73] = 1
for _op in range(0x74, 0x79):
    _fmt[_op] = 3
_fmt[0x79] = 2
_fmt[0x7a] = 1
for _op in range(0x7b, 0x80):
    _fmt[_op] = 1
_fmt[0x80] = 1
_fmt[0x81] = 1
# 0x82..0xaf: 22t/22s/23x/22b/22c (2 units)
for _op in range(0x82, 0xE3):
    _fmt[_op] = 2
_fmt[0xE3] = 2
# 0xf0..0xf9: 45cc/4rcc (4 units);  0xfa..0xff: 35c/3rc (3 units)
for _op in range(0xF0, 0xFA):
    _fmt[_op] = 4
for _op in range(0xFA, 0x100):
    _fmt[_op] = 3
# correct a few dense ranges that are genuinely 1 unit
for _op in (0x7b, 0x7c, 0x7d, 0x7e, 0x7f, 0x80, 0x81, 0x73, 0x3e, 0x27, 0x28):
    _fmt[_op] = 1

# opcodes that carry a string index
STRING_OPS = {0x1a, 0x1b}
# opcodes carrying a type index
TYPE_OPS = {0x1c, 0x1f, 0x20, 0x21, 0x22}
# opcodes carrying a field index
FIELD_OPS = {0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a,
             0x5b, 0x5c, 0x5d, 0x5e, 0x5f, 0x60, 0x61, 0x62, 0x63, 0x64,
             0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d}
# opcodes carrying a method index (invoke-kind: 35c / 3rc)
METHOD_OPS = {
    0x6e, 0x6f, 0x70, 0x71, 0x72,   # 35c: invoke-virtual..super, unused
    0x74, 0x75, 0x76, 0x77, 0x78,   # 3rc: invoke-virtual/range..super/range
}


def iter_code_units(insns: bytes):
    """Yield (byte_offset, opcode, size_units, raw_bytes)."""
    n = len(insns) // 2
    i = 0
    while i < n:
        off = i * 2
        op = insns[off]
        if op == 0x00:
            hi = insns[off + 1] if off + 1 < len(insns) else 0
            if hi == 0x01:      # packed-switch
                size = struct.unpack_from('<H', insns, off + 2)[0]
                total = size * 2 + 4
                yield off, op, total, insns[off:off + total * 2]
                i += total
                continue
            if hi == 0x02:      # sparse-switch
                size = struct.unpack_from('<H', insns, off + 2)[0]
                total = size * 4 + 2
                yield off, op, total, insns[off:off + total * 2]
                i += total
                continue
            if hi == 0x03:      # fill-array-data
                size = struct.unpack_from('<I', insns, off + 2)[0]
                elem_w = struct.unpack_from('<H', insns, off + 6)[0]
                total = 4 + (size * elem_w + 1) // 2
                yield off, op, total, insns[off:off + total * 2]
                i += total
                continue
            # plain nop
            yield off, 0x00, 1, insns[off:off + 2]
            i += 1
            continue
        size = _fmt.get(op, 1)
        yield off, op, size, insns[off:off + size * 2]
        i += size


def refs_from_code(insns: bytes):
    """Return dict of referenced string/type/field/method indices.

    Instruction operand layout (little-endian 16-bit code units):
      21c  const-string    AA|op BBBB     -> index in unit 1 (byte offset 2)
      31c  const-string/jumbo AA|op BBBBBBBB -> index in units 1-2 (offset 2)
      21c  const-class     AA|op BBBB
      22c  new-instance    A|op BBBB
      21c  check-cast      AA|op BBBB
      22c  instance-of     A|op BBBB
      22c/21c field ops    -> index at byte offset 2
      35c  invoke-kind     A|G|op BBBB F|E|D|C -> method index at byte offset 2
      3rc  invoke-kind/range AA|op BBBB CCCC    -> method index at byte offset 2
    The `raw` slice is exactly the instruction, so offset 2 is always correct
    for these forms, but must be bounds-checked for 1-unit instructions.
    """
    out = {'strings': set(), 'types': set(), 'fields': set(), 'methods': set()}

    def u16(buf, off):
        if off + 2 > len(buf):
            return None
        return struct.unpack_from('<H', buf, off)[0]

    for off, op, size, raw in iter_code_units(insns):
        try:
            if op == 0x1a:                                  # const-string (21c)
                v = u16(raw, 2)
                if v is not None:
                    out['strings'].add(v)
            elif op == 0x1b:                                # const-string/jumbo (31c)
                if len(raw) >= 4:
                    out['strings'].add(struct.unpack_from('<I', raw, 2)[0])
            elif op in TYPE_OPS:
                v = u16(raw, 2)
                if v is not None:
                    out['types'].add(v)
            elif op in FIELD_OPS:
                v = u16(raw, 2)
                if v is not None:
                    out['fields'].add(v)
            elif op in METHOD_OPS:
                v = u16(raw, 2)
                if v is not None:
                    out['methods'].add(v)
        except Exception:
            pass
    return out
