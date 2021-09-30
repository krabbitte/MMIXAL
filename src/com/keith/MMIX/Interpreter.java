package com.keith.MMIX;

import java.awt.desktop.SystemEventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void> {

    private int pc = 0;
    private boolean halt = false;

    void interpret(List<Stmt> statements) {
        try {
            while(!halt) {
                execute(statements.get(pc));
                pc++;
            }
        } catch (RuntimeError error) {
            MMIX.runtimeError(error);
        }
    }

    @Override
    public Void visitADD(Stmt.ADD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        int x;
        byte[] num;

        x = (int)arg2 + (int)arg3;

        num = new byte[] {
                (byte)((x >> 24) & 0xff),
                (byte)((x >> 16) & 0xff),
                (byte)((x >> 8) & 0xff),
                (byte)(x & 0xff),
        };

        if(arg1 instanceof Integer) {
            for (int i = 0; i < num.length; i++) {
                MMIX.environment.memory[(int) arg1][i] = num[i];
            }
        } else if(arg1 instanceof Token) {
            System.out.println(((Token)arg1).lexeme.substring(1,2));
            for (int i = 0; i < num.length; i++) {
                MMIX.environment.special[Integer.parseInt(((Token)arg1).lexeme.substring(1,2))][i] = num[i];
            }
        }

        x = (num[0] & 0xff) << 24 |
            (num[1] & 0xff) << 16 |
            (num[2] & 0xff) << 8  |
            (num[3] & 0xff);

        System.out.println("ADD: M[" + (int)arg1 + "] <- " + x);

        return null;
    }

    @Override
    public Void visitSUB(Stmt.SUB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        int x = (int)arg1 - (int)arg2;

        System.out.println("Sub done: " + x);

        return null;
    }

    @Override
    public Void visitMUL(Stmt.MUL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("Mul done");

        return null;
    }

    @Override
    public Void visitDIV(Stmt.DIV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("DIV done");

        return null;
    }

    @Override
    public Void visitLDB(Stmt.LDB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDB done");

        return null;
    }

    @Override
    public Void visitLDW(Stmt.LDW stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDW done");

        return null;
    }

    @Override
    public Void visitLDT(Stmt.LDT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDT done");

        return null;
    }

    @Override
    public Void visitLDO(Stmt.LDO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDO done");

        return null;
    }

    @Override
    public Void visitLDHT(Stmt.LDHT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDHT done");

        return null;
    }

    @Override
    public Void visitLDA(Stmt.LDA stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDA done");

        return null;
    }

    @Override
    public Void visitSTB(Stmt.STB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STB done");

        return null;
    }

    @Override
    public Void visitSTW(Stmt.STW stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STW done");

        return null;
    }

    @Override
    public Void visitSTT(Stmt.STT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STT done");

        return null;
    }

    @Override
    public Void visitSTO(Stmt.STO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STO done");

        return null;
    }

    @Override
    public Void visitSTHT(Stmt.STHT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STHT done");

        return null;
    }

    @Override
    public Void visitSTCO(Stmt.STCO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STCO done");

        return null;
    }

    @Override
    public Void visitNEG(Stmt.NEG stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("NEG done");

        return null;
    }

    @Override
    public Void visitAND(Stmt.AND stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("AND done");

        return null;
    }

    @Override
    public Void visitOR(Stmt.OR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("OR done");

        return null;
    }

    @Override
    public Void visitXOR(Stmt.XOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("XOR done");

        return null;
    }

    @Override
    public Void visitANDN(Stmt.ANDN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDN done");

        return null;
    }

    @Override
    public Void visitORN(Stmt.ORN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORN done");

        return null;
    }

    @Override
    public Void visitNAND(Stmt.NAND stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("NAND done");

        return null;
    }

    @Override
    public Void visitNOR(Stmt.NOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("NOR done");

        return null;
    }

    @Override
    public Void visitNXOR(Stmt.NXOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("NXOR done");

        return null;
    }

    @Override
    public Void visitMUX(Stmt.MUX stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("MUX done");

        return null;
    }

    @Override
    public Void visitBDIF(Stmt.BDIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BDIF done");

        return null;
    }

    @Override
    public Void visitWDIF(Stmt.WDIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("WDIF done");

        return null;
    }

    @Override
    public Void visitTDIF(Stmt.TDIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("TDIF done");

        return null;
    }

    @Override
    public Void visitODIF(Stmt.ODIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ODIF done");

        return null;
    }

    @Override
    public Void visitSADD(Stmt.SADD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SADD done");

        return null;
    }

    @Override
    public Void visitMOR(Stmt.MOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("MOR done");

        return null;
    }

    @Override
    public Void visitMXOR(Stmt.MXOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("MXOR done");

        return null;
    }

    @Override
    public Void visitSETH(Stmt.SETH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETH done");

        return null;
    }

    @Override
    public Void visitSETMH(Stmt.SETMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETMH done");

        return null;
    }

    @Override
    public Void visitSETML(Stmt.SETML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETML done");

        return null;
    }

    @Override
    public Void visitSETL(Stmt.SETL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETL done");

        return null;
    }

    @Override
    public Void visitINCH(Stmt.INCH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCH done");

        return null;
    }

    @Override
    public Void visitINCMH(Stmt.INCMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCMH done");

        return null;
    }

    @Override
    public Void visitINCML(Stmt.INCML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCML done");

        return null;
    }

    @Override
    public Void visitINCL(Stmt.INCL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCL done");

        return null;
    }

    @Override
    public Void visitORH(Stmt.ORH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORH done");

        return null;
    }

    @Override
    public Void visitORMH(Stmt.ORMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORMH done");

        return null;
    }

    @Override
    public Void visitORML(Stmt.ORML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORML done");

        return null;
    }

    @Override
    public Void visitORL(Stmt.ORL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORL done");

        return null;
    }

    @Override
    public Void visitANDNH(Stmt.ANDNH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNH done");

        return null;
    }

    @Override
    public Void visitANDNMH(Stmt.ANDNMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNMH done");

        return null;
    }

    @Override
    public Void visitANDNML(Stmt.ANDNML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNML done");

        return null;
    }

    @Override
    public Void visitANDNL(Stmt.ANDNL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNL done");

        return null;
    }

    @Override
    public Void visitSL(Stmt.SL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SL done");

        return null;
    }

    @Override
    public Void visitSLU(Stmt.SLU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SLU done");

        return null;
    }

    @Override
    public Void visitSR(Stmt.SR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SR done");

        return null;
    }

    @Override
    public Void visitSRU(Stmt.SRU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SRU done");

        return null;
    }

    @Override
    public Void visitCMP(Stmt.CMP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CMP done");

        return null;
    }

    @Override
    public Void visitCMPU(Stmt.CMPU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CMPU done");

        return null;
    }

    @Override
    public Void visitCSN(Stmt.CSN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSN done");

        return null;
    }

    @Override
    public Void visitCSZ(Stmt.CSZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSZ done");

        return null;
    }

    @Override
    public Void visitCSP(Stmt.CSP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSP done");

        return null;
    }

    @Override
    public Void visitCSOD(Stmt.CSOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSOD done");

        return null;
    }

    @Override
    public Void visitCSNN(Stmt.CSNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSNN done");

        return null;
    }

    @Override
    public Void visitCSNZ(Stmt.CSNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSNZ done");

        return null;
    }

    @Override
    public Void visitCSNP(Stmt.CSNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSNP done");

        return null;
    }

    @Override
    public Void visitCSEV(Stmt.CSEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSEV done");

        return null;
    }

    @Override
    public Void visitZSN(Stmt.ZSN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSN done");

        return null;
    }

    @Override
    public Void visitZSZ(Stmt.ZSZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSZ done");

        return null;
    }

    @Override
    public Void visitZSP(Stmt.ZSP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSP done");

        return null;
    }

    @Override
    public Void visitZSOD(Stmt.ZSOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSOD done");

        return null;
    }

    @Override
    public Void visitZSNN(Stmt.ZSNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSNN done");

        return null;
    }

    @Override
    public Void visitZSNZ(Stmt.ZSNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSNZ done");

        return null;
    }

    @Override
    public Void visitZSNP(Stmt.ZSNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSNP done");

        return null;
    }

    @Override
    public Void visitZSEV(Stmt.ZSEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ZSEV done");

        return null;
    }

    @Override
    public Void visitBN(Stmt.BN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BN done");

        return null;
    }

    @Override
    public Void visitBZ(Stmt.BZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BZ done");

        return null;
    }

    @Override
    public Void visitBP(Stmt.BP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BP done");

        return null;
    }

    @Override
    public Void visitBOD(Stmt.BOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BOD done");

        return null;
    }

    @Override
    public Void visitBNN(Stmt.BNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BNN done");

        return null;
    }

    @Override
    public Void visitBNZ(Stmt.BNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BNZ done");

        return null;
    }

    @Override
    public Void visitBNP(Stmt.BNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BNP done");

        return null;
    }

    @Override
    public Void visitBEV(Stmt.BEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BEV done");

        return null;
    }

    @Override
    public Void visitPBN(Stmt.PBN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBN done");

        return null;
    }

    @Override
    public Void visitPBZ(Stmt.PBZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBZ done");

        return null;
    }

    @Override
    public Void visitPBP(Stmt.PBP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBP done");

        return null;
    }

    @Override
    public Void visitPBOD(Stmt.PBOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBOD done");

        return null;
    }

    @Override
    public Void visitPBNN(Stmt.PBNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBNN done");

        return null;
    }

    @Override
    public Void visitPBNZ(Stmt.PBNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBNZ done");

        return null;
    }

    @Override
    public Void visitPBNP(Stmt.PBNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBNP done");

        return null;
    }

    @Override
    public Void visitPBEV(Stmt.PBEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBEV done");

        return null;
    }

    @Override
    public Void visitGETA(Stmt.GETA stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("GETA done");

        return null;
    }

    @Override
    public Void visitGO(Stmt.GO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("GO done");

        return null;
    }

    @Override
    public Void visitFADD(Stmt.FADD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FADD done");

        return null;
    }

    @Override
    public Void visitFSUB(Stmt.FSUB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FSUB done");

        return null;
    }

    @Override
    public Void visitFMUL(Stmt.FMUL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FMUL done");

        return null;
    }

    @Override
    public Void visitFDIV(Stmt.FDIV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FDIV done");

        return null;
    }

    @Override
    public Void visitFREM(Stmt.FREM stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FREM done");

        return null;
    }

    @Override
    public Void visitFSQRT(Stmt.FSQRT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FSQRT done");

        return null;
    }

    @Override
    public Void visitFINT(Stmt.FINT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FINT done");

        return null;
    }

    @Override
    public Void visitFCMP(Stmt.FCMP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FCMP done");

        return null;
    }

    @Override
    public Void visitFEQL(Stmt.FEQL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FEQL done");

        return null;
    }

    @Override
    public Void visitFUN(Stmt.FUN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FUN done");

        return null;
    }

    @Override
    public Void visitFCMPE(Stmt.FCMPE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FCMPE done");

        return null;
    }

    @Override
    public Void visitFEQLE(Stmt.FEQLE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FEQLE done");

        return null;
    }

    @Override
    public Void visitFUNE(Stmt.FUNE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FUNE done");

        return null;
    }

    @Override
    public Void visitLDSF(Stmt.LDSF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDSF done");

        return null;
    }

    @Override
    public Void visitSTSF(Stmt.STSF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STSF done");

        return null;
    }

    @Override
    public Void visitFIX(Stmt.FIX stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FIX done");

        return null;
    }

    @Override
    public Void visitFIXU(Stmt.FIXU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FIXU done");

        return null;
    }

    @Override
    public Void visitFLOT(Stmt.FLOT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FLOT done");

        return null;
    }

    @Override
    public Void visitFLOTU(Stmt.FLOTU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FLOTU done");

        return null;
    }

    @Override
    public Void visitPUSHJ(Stmt.PUSHJ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PUSHJ done");

        return null;
    }

    @Override
    public Void visitPUSHGO(Stmt.PUSHGO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PUSHGO done");

        return null;
    }

    @Override
    public Void visitLDUNC(Stmt.LDUNC stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDUNC done");

        return null;
    }

    @Override
    public Void visitSTUNC(Stmt.STUNC stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STUNC done");

        return null;
    }

    @Override
    public Void visitPRELD(Stmt.PRELD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PRELD done");

        return null;
    }

    @Override
    public Void visitPREGO(Stmt.PREGO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PREGO done");

        return null;
    }

    @Override
    public Void visitPREST(Stmt.PREST stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PREST done");

        return null;
    }

    @Override
    public Void visitCSWAP(Stmt.CSWAP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSWAP done");

        return null;
    }

    @Override
    public Void visitSYNC(Stmt.SYNC stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SYNC done");

        return null;
    }

    @Override
    public Void visitRESUME(Stmt.RESUME stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("RESUME done");

        return null;
    }

    @Override
    public Void visitGET(Stmt.GET stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("GET done");

        return null;
    }

    @Override
    public Void visitPUT(Stmt.PUT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PUT done");

        return null;
    }

    @Override
    public Void visitSAVE(Stmt.SAVE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SAVE done");

        return null;
    }

    @Override
    public Void visitUNSAVE(Stmt.UNSAVE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("UNSAVE done");

        return null;
    }

    @Override
    public Void visitLDVTS(Stmt.LDVTS stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDVTS done");

        return null;
    }

    @Override
    public Void visitSWYM(Stmt.SWYM stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SWYM done");

        return null;
    }

    @Override
    public Void visitJMP(Stmt.JMP stmt) {
        Object arg = evaluate(stmt.args.get(0));
        int line = 0;

        if(arg instanceof Integer) {
            line = (int)arg;
        } else if(arg instanceof Token) {
            line = (int)((Token)arg).literal;
        }

        System.out.println("JMP: pc <- " + line);

        if(line != 0) {
            this.pc = line - 2;
        }

        return null;
    }

    @Override
    public Void visitIS(Stmt.IS stmt) {

        Object value = evaluate(stmt.value);
        Object key = stmt.key.lexeme;

        MMIX.environment.values.put((String)key, value);

        return null;
    }

    @Override
    public Void visitTRIP(Stmt.TRIP stmt) {

        System.out.println("HALT");
        halt = true;

        return null;
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {

        Object value = null;
        if (MMIX.environment.values.containsKey(expr.name.lexeme))
        {
            value = MMIX.environment.values.get(expr.name.lexeme);
        }
        else if(expr.name.type.ordinal() >= 29 && expr.name.type.ordinal() <= 48)
        {

            for(int i = 0; i < MMIX.environment.locals.size(); i++) {

                if(MMIX.environment.locals.get(i) == expr.name) {

                    if(expr.name.type.ordinal() >= 39)
                    {
                        for(int j = i; j > 0; j--) {
                            if(MMIX.environment.locals.get(j).type.ordinal() == expr.name.type.ordinal() - 20)
                            {
                                value = MMIX.environment.locals.get(j).line;
                                return value;
                            }
                        }
                    }

                    if(expr.name.type.ordinal() <= 38)
                    {
                        for(int j = i; j < MMIX.environment.locals.size(); j++) {
                            if(MMIX.environment.locals.get(j).type.ordinal() == expr.name.type.ordinal() - 10)
                            {
                                value = MMIX.environment.locals.get(j).line;
                                return value;
                            }
                        }
                    }
                }

            }

        } else
        {
            MMIX.error(expr.name,"identifier does not correspond to value");
        }

        return value;
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) { return expr.value; }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch(expr.operator.type) {
            case BANG:
            case MINUS:
                checkNumberOperand(expr.operator, right);
                return -(Integer)right;
        }

        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitLabelExpr(Expr.Label expr) {
        return MMIX.environment.labels.get(expr.label.lexeme);
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch(expr.operator.type) {
            case PLUS:
                checkNumberOperands(expr.operator, left, right);
                return (int)left + (int)right;
            case MINUS:
                checkNumberOperands(expr.operator, left, right);
                return (int)left - (int)right;
            case SLASH:
                checkNumberOperands(expr.operator, left, right);
                return (int)left / (int)right;
            case STAR:
                checkNumberOperands(expr.operator, left, right);
                return (int)left * (int)right;
            case LESS_LESS:
                checkNumberOperands(expr.operator, left, right);
                return (int)left << (int)right;
        }

        return null;
    }

    private void checkNumberOperand(Token operator, Object operand) {
        if(operand instanceof Integer) return;
        throw new RuntimeError(operator, "Operand must be a number");
    }

    private void checkNumberOperands(Token operator, Object left, Object right) {
        System.out.println(left.getClass() + " " + right.getClass());
        if(left instanceof Integer && right instanceof Integer) return;
        throw new RuntimeError(operator, "Operands must be numbers");
    }

    private Object evaluate(Expr expr) { return expr.accept(this); }

    private void execute(Stmt stmt) { stmt.accept(this); }
}
