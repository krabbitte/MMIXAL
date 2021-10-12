package com.MMIX;

import java.util.*;

import static com.MMIX.TokenType.*;

class Parser {
    private static class ParseError extends RuntimeException {}

    private List<Token> tokens;
    private int current = 0;
    private String prefix = "";

    private static final Map<TokenType, Integer> opArgs;

    static {
        opArgs = new HashMap<TokenType, Integer>();

        opArgs.put(ADD, 3);
        opArgs.put(SUB, 3);
        opArgs.put(MUL, 3);
        opArgs.put(DIV, 3);

        opArgs.put(ADDU, 3);
        opArgs.put(SUBU, 3);
        opArgs.put(MULU, 3);
        opArgs.put(DIVU, 3);

        opArgs.put(NEG, 2);
        opArgs.put(NEGU, 2)
        ;
        opArgs.put(CMP, 3);
        opArgs.put(CMPU, 3);
        opArgs.put(FLOT, 2);
        opArgs.put(FLOTU, 2);
        opArgs.put(FIX, 2);
        opArgs.put(FIXU, 3);

        opArgs.put(FADD, 3);
        opArgs.put(FSUB, 3);
        opArgs.put(FMUL, 3);
        opArgs.put(FDIV, 3);
        opArgs.put(FREM, 3);
        opArgs.put(FSQRT, 3);
        opArgs.put(FINT, 3);

        opArgs.put(FCMP, 3);
        opArgs.put(FEQL, 3);
        opArgs.put(FUN, 3);
        opArgs.put(FCMPE, 3);
        opArgs.put(FEQLE, 3);
        opArgs.put(FUNE, 3);

        opArgs.put(SFLOT, 2);
        opArgs.put(SFLOTU, 2);

        opArgs.put(AND, 3);
        opArgs.put(OR, 3);
        opArgs.put(XOR, 3);
        opArgs.put(ANDN, 3);
        opArgs.put(ORN, 3);
        opArgs.put(NAND, 3);
        opArgs.put(NOR, 3);
        opArgs.put(NXOR, 3);

        opArgs.put(SL, 3);
        opArgs.put(SLU, 3);
        opArgs.put(SR, 3);
        opArgs.put(SRU, 3);

        opArgs.put(MOR, 3);
        opArgs.put(MXOR, 3);

        opArgs.put(BDIF, 3);
        opArgs.put(WDIF, 3);
        opArgs.put(TDIF, 3);
        opArgs.put(ODIF, 3);

        opArgs.put(ORH, 3);
        opArgs.put(ORMH, 3);
        opArgs.put(ORML, 3);
        opArgs.put(ORL, 3);
        opArgs.put(ANDNH, 3);
        opArgs.put(ANDNMH, 3);
        opArgs.put(ANDNML, 3);
        opArgs.put(ANDNL, 3);

        opArgs.put(SADD, 3);

        opArgs.put(MUX, 3);

        opArgs.put(LDA, 3);

        opArgs.put(GETA, 3);

        opArgs.put(ADDU2, 3);
        opArgs.put(ADDU4, 3);
        opArgs.put(ADDU8, 3);
        opArgs.put(ADDU16, 3);

        opArgs.put(SET, 2);
        opArgs.put(SETH, 2);
        opArgs.put(SETMH, 2);
        opArgs.put(SETML, 2);
        opArgs.put(SETL, 2);

        opArgs.put(INCH, 3);
        opArgs.put(INCMH, 3);
        opArgs.put(INCML, 3);
        opArgs.put(INCL, 3);

        opArgs.put(LDB, 3);
        opArgs.put(LDW, 3);
        opArgs.put(LDT, 3);
        opArgs.put(LDO, 3);

        opArgs.put(STB, 3);
        opArgs.put(STW, 3);
        opArgs.put(STT, 3);
        opArgs.put(STO, 3);

        opArgs.put(STCO, 3);

        opArgs.put(LDBU, 3);
        opArgs.put(LDWU, 3);
        opArgs.put(LDTU, 3);
        opArgs.put(LDOU, 3);

        opArgs.put(STBU, 3);
        opArgs.put(STWU, 3);
        opArgs.put(STTU, 3);
        opArgs.put(STOU, 3);

        opArgs.put(LDHT, 3);

        opArgs.put(STHT, 3);

        opArgs.put(LDSF, 3);
        opArgs.put(STSF, 3);

        opArgs.put(JMP, 1);

        opArgs.put(GO, 3);

        opArgs.put(BZ, 2);
        opArgs.put(BNZ, 2);
        opArgs.put(BN, 2);
        opArgs.put(BNN, 2);
        opArgs.put(BP, 2);
        opArgs.put(BNP, 2);
        opArgs.put(BOD, 2);
        opArgs.put(BEV, 2);

        opArgs.put(CSZ, 3);
        opArgs.put(CSNZ, 3);
        opArgs.put(CSN, 3);
        opArgs.put(CSNN, 3);
        opArgs.put(CSP, 3);
        opArgs.put(CSNP, 3);
        opArgs.put(CSOD, 3);
        opArgs.put(CSEV, 3);

        opArgs.put(PUSHJ, 3);
        opArgs.put(PUSHGO, 3);

        opArgs.put(POP, 3);

        opArgs.put(SAVE, 2);
        opArgs.put(UNSAVE, 1);

        opArgs.put(CSWAP, 3);

        opArgs.put(PUT, 2);
        opArgs.put(GET, 2);

        opArgs.put(TRIP, 3);
        opArgs.put(TRAP, 3);

        opArgs.put(RESUME, 1);

        opArgs.put(LDUNC, 3);
        opArgs.put(STUNC, 3);

        opArgs.put(PRELD, 3);
        opArgs.put(PREST, 3);
        opArgs.put(PREGO, 3);

        opArgs.put(SYNC, 3);
        opArgs.put(SYNCID, 3);
        opArgs.put(SYNCD, 3);

        opArgs.put(LDVTS, 3);

        opArgs.put(SWYM, 3);

        opArgs.put(IS, 1);
        opArgs.put(LOC, 1);

        opArgs.put(BYTE, 1);
        opArgs.put(WYDE, 1);
        opArgs.put(TETRA, 1);
        opArgs.put(OCTA, 1);

        opArgs.put(GREG, 1);

        opArgs.put(PREFIX, 1);

        opArgs.put(TRIP, 0);
    }

    Parser(List<Token> tokens) { this.tokens = tokens; }

    List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        labelsInit();

        while(!isAtEnd()) {
            Stmt temp = pseud();

            if(temp != null) {
                statements.add(temp);
            }
        }

        return statements;
    }

    private Stmt pseud() {

        if(match(PREFIX)) {
            prefix();
            return null;
        }

        if(tokens.get(current).type == IDENTIFIER && tokens.get(current + 1).type == IS) {
            is();
            return null;
        }

        if(tokens.get(current).type == IDENTIFIER && tokens.get(current + 1).type == GREG) {
            greg();
            return null;
        }

        Stmt value = instruction();

        return value;
    }

    private void prefix() {
        consume("Where's the colon?", COLON);
        System.out.println("Prefix before = " + this.prefix);
        if(match(IDENTIFIER)) {
            this.prefix += previous().lexeme;
            consume("where's the newline?", EOL);
        } else if (match(EOL)) {
            this.prefix = ":";
        }
        System.out.println("Prefix after = " + this.prefix);
    }

    private Void is() {
        Token key = consume("Where's the identifier", IDENTIFIER);

        Token opcode = consume("wheres the is?", IS);

        if(tokens.get(current).type == REG) {
            MMIX.environment.values.put(key.lexeme, advance());
        } else {
            Expr value = expression();
        }

        consume("wheres the newline/", EOL);

        MMIX.environment.incPcOffset();

        return null;
    }

    private Void greg() {
        Token key = consume("Where's the identifier", IDENTIFIER);

        Token opcode = consume("wheres the GREG?", GREG);

        int regNum = MMIX.environment.decG();
        Token reg = new Token(REG, "$" + regNum, regNum, key.line);


        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).lexeme.equals(key.lexeme)) {
                tokens.remove(i);
                tokens.add(i, reg);
            }
        }


        consume("wheres the newline/", EOL);

        MMIX.environment.incPcOffset();

        return null;
    }

    private Stmt instruction() {
        Expr label;
        List<Expr> args = new ArrayList<>();

        if(tokens.get(current).lexeme.toLowerCase().equals("string")) {
            string();
            advance();
            return null;
        } else if(tokens.get(current).type == IDENTIFIER) {
            label = label();
            if(tokens.get(current).lexeme.toLowerCase().equals("main")) MMIX.environment.setStartPC(tokens.get(current).line - MMIX.environment.getPcOffset() - 1);
            advance();
        } else if(tokens.get(current).type.ordinal() >= 19 && tokens.get(current).type.ordinal() <= 48){
            label = label();
            advance();
        } else {
            label = null;
        }

        Token opcode = consumeOP("Unidentified opcode.");
        int numArgs = opArgs.get(opcode.type);


        for(int i = 0; i < numArgs; i++) {
            args.add(expression());
            if(i != numArgs - 1) {
                consume("wheres the comma?", COMMA);
            }
        }

        consume("Where's the newline?", EOL);

        switch (opcode.type) {
            case ADD:
                return new Stmt.ADD(label, args, opcode.line);
            case SUB:
                return new Stmt.SUB(label, args, opcode.line);
            case MUL:
                return new Stmt.MUL(label, args, opcode.line);
            case DIV:
                return new Stmt.DIV(label, args, opcode.line);
            case LDB:
                return new Stmt.LDB(label, args, opcode.line);
            case LDW:
                return new Stmt.LDW(label, args, opcode.line);
            case LDT:
                return new Stmt.LDT(label, args, opcode.line);
            case LDO:
                return new Stmt.LDO(label, args, opcode.line);
            case LDHT:
                return new Stmt.LDHT(label, args, opcode.line);
            case STB:
                return new Stmt.STB(label, args, opcode.line);
            case STW:
                return new Stmt.STW(label, args, opcode.line);
            case STT:
                return new Stmt.STT(label, args, opcode.line);
            case STO:
                return new Stmt.STO(label, args, opcode.line);
            case STHT:
                return new Stmt.STHT(label, args, opcode.line);
            case STCO:
                return new Stmt.STCO(label, args, opcode.line);
            case LDA:
                return new Stmt.LDA(label, args, opcode.line);
            case SET:
                return new Stmt.SETL(label, args, opcode.line);
            case SADD:
                return new Stmt.SADD(label, args, opcode.line);
            case CMP:
                return new Stmt.CMP(label, args, opcode.line);
            case BN:
                return new Stmt.BN(label, args, opcode.line);
            case JMP:
                return new Stmt.JMP(label, args, opcode.line);
            case TRIP:
                return new Stmt.TRIP(label, args, opcode.line);
        }

        MMIX.error(0,"could not resolve instruction");
        return null;
    }

    private Expr label() {
        Token label = tokens.get(current);

        if(label.type.ordinal() >= 29 && label.type.ordinal() <= 48) {
            MMIX.error(tokens.get(current), "wrong local symbol");
            return null;
        }

        return new Expr.Label(label);
    }

    private Void string() {
        return null;
    }

    private Expr expression() {
        return term();
    }

    private Expr term() {
        Expr expr = factor();

        while(match(MINUS, PLUS)) {
            Token operator = previous();
            Expr right = factor();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;
    }

    private Expr factor() {
        Expr expr = unary();

        while(match(SLASH, STAR)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;
    }

    private Expr unary() {
        if(match(BANG, MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Expr.Unary(operator, right);
        }

        return primary();
    }

    private Expr primary() {

        if(match(IDENTIFIER)) {

            if(MMIX.environment.labels.containsKey(previous().lexeme)) {
                Token label = previous();
                label.literal = MMIX.environment.labels.get(previous().lexeme);
                return new Expr.Label(label);
            }

            previous().lexeme = this.prefix + (String)previous().lexeme;

            return new Expr.Variable(previous());
        }

        if(match(REG)) {
            return new Expr.Variable(previous());
        }

        if(tokens.get(current).type.ordinal() >= 29 && tokens.get(current).type.ordinal() <= 48) {
            advance();
            return new Expr.Variable(previous());
        }

        if(match(NUMBER, STRING)) {
            return new Expr.Literal(previous().literal);
        }

        if(match(LEFT_PAREN)) {
            Expr expr = expression();
            consume("Expect ')' after expression", RIGHT_PAREN);
            return new Expr.Grouping(expr);
        }

        throw error(peek(), "Expect expression.");
    }

    private boolean match(TokenType... types) {
        for(TokenType type : types) {
            if(check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(String message, TokenType... types) {

        for(TokenType type : types) {
            if(check(type)) return advance();
        }

        throw error(peek(), message);
    }

    private Token consumeOP(String message) {

        if(tokens.get(current).type.ordinal() >= 60 && tokens.get(current).type.ordinal() <= 256) {
            return advance();
        }

        throw error(peek(), message);
    }

    private boolean check(TokenType type) {
        if(isAtEnd()) return false;
        return peek().type == type;
    }

    private Token advance() {
        if(!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() { return peek().type == EOF; }

    private Token peek() { return tokens.get(current); }

    private Token previous() { return tokens.get(current - 1); }

    private Void labelsInit() {
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).type == IDENTIFIER && (tokens.get(i+1).type.ordinal() >= 50 && tokens.get(i+1).type.ordinal() <= 193) && tokens.get(i+1).type != IS) {
                MMIX.environment.labels.put(tokens.get(i).lexeme, tokens.get(i).line);
            }

            if(tokens.get(i).type.ordinal() >= 19 && tokens.get(i).type.ordinal() <= 48) {
                MMIX.environment.locals.add(tokens.get(i));
            }
        }

        return null;
    }

    private ParseError error(Token token, String message) {
        MMIX.error(token, message);
        return new ParseError();
    }
}
