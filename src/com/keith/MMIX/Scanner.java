package com.keith.MMIX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.keith.MMIX.TokenType.*;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("H0",  H0); keywords.put("B0", B0); keywords.put("F0", F0);
        keywords.put("H1",  H1); keywords.put("B1", B1); keywords.put("F1", F1);
        keywords.put("H2",  H2); keywords.put("B2", B2); keywords.put("F2", F2);
        keywords.put("H3",  H3); keywords.put("B3", B3); keywords.put("F3", F3);
        keywords.put("H4",  H4); keywords.put("B4", B4); keywords.put("F4", F4);
        keywords.put("H5",  H5); keywords.put("B5", B5); keywords.put("F5", F5);
        keywords.put("H6",  H6); keywords.put("B6", B6); keywords.put("F6", F6);
        keywords.put("H7",  H7); keywords.put("B7", B7); keywords.put("F7", F7);
        keywords.put("H8",  H8); keywords.put("B8", B8); keywords.put("F8", F8);
        keywords.put("H9",  H9); keywords.put("B9", B9); keywords.put("F9", F9);

        keywords.put("IS",      IS);
        keywords.put("LOC",     LOC);
        keywords.put("BYTE",    BYTE);
        keywords.put("WYDE",    WYDE);
        keywords.put("TETRA",   TETRA);
        keywords.put("OCTA",    OCTA);
        keywords.put("GREG",    GREG);
        keywords.put("PREFIX",  PREFIX);
        keywords.put("LOCAL",   LOCAL);
        keywords.put("BSPEC",   BSPEC);
        keywords.put("ESPEC",   ESPEC);

        keywords.put("ADD", ADD);
        keywords.put("SUB", SUB);
        keywords.put("MUL", MUL);
        keywords.put("DIV", DIV);

        keywords.put("ADDU", ADDU);
        keywords.put("SUBU", SUBU);
        keywords.put("MULU", MULU);
        keywords.put("DIVU", DIVU);

        keywords.put("NEG", NEG);
        keywords.put("NEGU", NEGU);

        keywords.put("CMP", CMP);
        keywords.put("CMPU", CMPU);

        keywords.put("FLOT", FLOT);
        keywords.put("FLOTU", FLOTU);

        keywords.put("FIX", FIX);
        keywords.put("FIXU", FIXU);

        keywords.put("FADD", FADD);
        keywords.put("FSUB", FSUB);
        keywords.put("FMUL", FMUL);
        keywords.put("FDIV", FDIV);
        keywords.put("FREM", FREM);
        keywords.put("FSQRT", FSQRT);
        keywords.put("FINT", FINT);

        keywords.put("FCMP", FCMP);
        keywords.put("FEQL", FEQL);
        keywords.put("FUN", FUN);
        keywords.put("FEQLE", FCMPE);
        keywords.put("FUNE", FUNE);

        keywords.put("SFLOT", SFLOT);
        keywords.put("SFLOTU", SFLOTU);

        keywords.put("AND", AND);
        keywords.put("OR", OR);
        keywords.put("XOR", XOR);
        keywords.put("ANDN", ANDN);
        keywords.put("ORN", ORN);
        keywords.put("NAND", NAND);
        keywords.put("NOR", NOR);
        keywords.put("NXOR", NXOR);

        keywords.put("SL", SL);
        keywords.put("SLU", SLU);
        keywords.put("SR", SR);
        keywords.put("SRU", SRU);

        keywords.put("MOR", MOR);
        keywords.put("MXOR", MXOR);

        keywords.put("BDIF", BDIF);
        keywords.put("WDIF", WDIF);
        keywords.put("TDIF", TDIF);
        keywords.put("ODIF", ODIF);

        keywords.put("ORH", ORH);
        keywords.put("ORMH", ORMH);
        keywords.put("ORML", ORML);
        keywords.put("ORL", ORL);
        keywords.put("ANDNH", ANDNH);
        keywords.put("ANDNMH", ANDNMH);
        keywords.put("ANDNML", ANDNML);
        keywords.put("ANDNL", ANDNL);

        keywords.put("SADD", SADD);
        keywords.put("MUX", MUX);
        keywords.put("LDA", LDA);
        keywords.put("GETA", GETA);

        keywords.put("ADDU2", ADDU2);
        keywords.put("ADDU4", ADDU4);
        keywords.put("ADDU8", ADDU8);
        keywords.put("ADDU16", ADDU16);

        keywords.put("SET", SET);
        keywords.put("SETH", SETH);
        keywords.put("SETMH", SETMH);
        keywords.put("SETML", SETML);
        keywords.put("SETL", SETL);

        keywords.put("INCH", INCH);
        keywords.put("INCMH", INCMH);
        keywords.put("INCML", INCML);
        keywords.put("INCL", INCL);

        keywords.put("LDB", LDB);
        keywords.put("LDW", LDW);
        keywords.put("LDT", LDT);
        keywords.put("LD0", LDO);

        keywords.put("STB", STB);
        keywords.put("STW", STW);
        keywords.put("STT", STT);
        keywords.put("STO", STO);

        keywords.put("STCO", STCO);

        keywords.put("LDBU", LDBU);
        keywords.put("LDWU", LDWU);
        keywords.put("LDTU", LDTU);
        keywords.put("LDOU", LDOU);

        keywords.put("STBU", STBU);
        keywords.put("STWU", STWU);
        keywords.put("STTU", STTU);
        keywords.put("STOU", STOU);

        keywords.put("LDHT", LDHT);

        keywords.put("STHT", STHT);

        keywords.put("LDSF", LDSF);
        keywords.put("STSF", STSF);

        keywords.put("JMP", JMP);
        keywords.put("GO", GO);

        keywords.put("BZ", BZ);
        keywords.put("BNZ", BNZ);
        keywords.put("BN", BN);
        keywords.put("BNN", BNN);
        keywords.put("BP", BP);
        keywords.put("BNP", BNP);
        keywords.put("BOD", BOD);
        keywords.put("BEV", BEV);

        keywords.put("CSZ", CSZ);
        keywords.put("CSNZ", CSNZ);
        keywords.put("CSN", CSN);
        keywords.put("CSNN", CSNN);
        keywords.put("CSP", CSP);
        keywords.put("CSNP", CSNP);
        keywords.put("CSOD", CSOD);
        keywords.put("CSEV", CSEV);

        keywords.put("PUSHJ", PUSHJ);
        keywords.put("PUSHGO", PUSHGO);

        keywords.put("POP", POP);

        keywords.put("SAVE", SAVE);
        keywords.put("UNSAVE", UNSAVE);

        keywords.put("CSWAP", CSWAP);

        keywords.put("PUT", PUT);
        keywords.put("GET", GET);

        keywords.put("TRIP", TRIP);
        keywords.put("TRAp", TRAP);

        keywords.put("RESUME", RESUME);

        keywords.put("LDUNC", LDUNC);
        keywords.put("STUNC", STUNC);


        keywords.put("PRELD", PRELD);
        keywords.put("PREST", PREST);
        keywords.put("PREGO", PREGO);

        keywords.put("SYNC", SYNC);
        keywords.put("SYNCID", SYNCID);
        keywords.put("SYNCD", SYNCD);

        keywords.put("LDVTS", LDVTS);
        keywords.put("SWYM", SWYM);
    }

    Scanner(String source) { this.source = source; }

    List<Token> scanTokens() {
        while(!isAtEnd()) {
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOL, "", null, line));
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();

        switch(c) {
            // basic single character tokens
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '+': addToken(PLUS); break;
            case '-': addToken(MINUS); break;
            case '*': addToken(STAR); break;
            case ':': addToken(COLON); break;
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '#': addToken(POUND); break;

            case '$':
                if(isDigit(peek())) {
                    register();
                } else {
                    MMIX.error(line, "wacky $");
                }
                break;

            // << operator
            case '<':
                if(match('<')) {
                    addToken(LESS_LESS);
                } else {
                    MMIX.error(line, "Unexpected token");
                }
                break;

            // Comments
            case ';':
            case '%':
                while(peek() != '\n' && !isAtEnd()) advance();
                break;
            case '/':
                if(match('/')) {
                    while(peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(SLASH);
                }
                break;

            // Whitespace
            case ' ':
            case '\r':
            case '\t':
                break;

            // Indicate the end of an instruction with a newline
            case '\n':
                addToken(EOL);
                line++;
                while(peek() == '\n') {
                    advance();
                    line++;
                }
                break;

            case '"': string(); break;

            default:
                if(isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    MMIX.error(line, "Unexpected character");
                }
                break;
        }
    }

    private void number() {
        while(isDigit(peek())) advance();

        addToken(NUMBER, Integer.parseInt(source.substring(start, current)));
    }

    private void identifier() {
        while(isAlphaNumberic(peek())) advance();

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if(type == null) type = IDENTIFIER;
        addToken(type);
    }

    private void string() {
        while(peek() != '"' && !isAtEnd()) {
            if(peek() == '\n') MMIX.error(line, "Newline where there shouldn't be.");
            advance();
        }

        if(isAtEnd()) {
            MMIX.error(line, "Unterminated string.");
        }

        // Consume closing "
        advance();

        // Trim surrounding quotes
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    private void register() {
        while(isDigit(peek())) advance();

        int num = Integer.parseInt(source.substring(start+1, current));

        addToken(REG, num);
    }

    private boolean match(char expected) {
        if(isAtEnd()) return false;
        if(source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private char peek() {
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if(current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return  (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isAlphaNumberic(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
