package com.keith.MMIX;

enum TokenType {
    // Single-character tokens
    COMMA, DOT, PLUS, MINUS, COLON, STAR, BANG, SEMICOLON, SLASH, PERCENT, DOLLAR, POUND,
    LEFT_PAREN, RIGHT_PAREN,

    // Multi character tokens
    LESS_LESS, REG,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Standard Labels
    H0, H1, H2, H3, H4,
    H5, H6, H7, H8, H9,

    F0, F1, F2, F3, F4,
    F5, F6, F7, F8, F9,

    B0, B1, B2, B3, B4,
    B5, B6, B7, B8, B9,

    PREFIX,

    // Assembler instructions
    IS, LOC, BYTE, WYDE, TETRA, OCTA, GREG, LOCAL, BSPEC, ESPEC,

    // Operations
    ADD, SUB, MUL, DIV,
    ADDU, SUBU, MULU, DIVU,
    NEG, NEGU,
    CMP, CMPU,
    FLOT, FLOTU,
    FIX, FIXU,
    FADD, FSUB, FMUL, FDIV, FREM, FSQRT, FINT,
    FCMP, FEQL, FUN, FCMPE, FEQLE, FUNE,
    SFLOT, SFLOTU,
    AND, OR, XOR, ANDN, ORN, NAND, NOR, NXOR,
    SL, SLU, SR, SRU,
    MOR, MXOR,
    BDIF, WDIF, TDIF, ODIF,
    ORH, ORMH, ORML, ORL, ANDNH, ANDNMH, ANDNML, ANDNL,
    SADD,
    MUX,
    LDA,
    GETA,
    ADDU2, ADDU4, ADDU8, ADDU16,
    SET, SETH, SETMH, SETML, SETL,
    INCH, INCMH, INCML, INCL,
    LDB, LDW, LDT, LDO,
    STB, STW, STT, STO,
    STCO,
    LDBU, LDWU, LDTU, LDOU,
    STBU, STWU, STTU, STOU,
    LDHT,
    STHT,
    LDSF, STSF,
    JMP,
    GO,
    BZ, BNZ, BN, BNN, BP, BNP, BOD, BEV,
    CSZ, CSNZ, CSN, CSNN, CSP, CSNP, CSOD, CSEV,
    PUSHJ, PUSHGO,
    POP,
    SAVE, UNSAVE,
    CSWAP,
    PUT, GET,
    TRIP,
    TRAP,
    RESUME,
    LDUNC, STUNC,
    PRELD, PREST, PREGO,
    SYNC,
    SYNCID,
    SYNCD,
    LDVTS,
    SWYM,

    EOL,
    EOF,
}
