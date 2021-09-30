package com.keith.MMIX;

import java.util.List;

abstract class Stmt {
    interface Visitor<R> {
        R visitADD(ADD stmt);
        R visitSUB(SUB stmt);
        R visitMUL(MUL stmt);
        R visitDIV(DIV stmt);
        R visitIS(IS stmt);
        R visitTRIP(TRIP stmt);
        R visitLDB(LDB stmt);
        R visitLDW(LDW stmt);
        R visitLDT(LDT stmt);
        R visitLDO(LDO stmt);
        R visitLDHT(LDHT stmt);
        R visitLDA(LDA stmt);
        R visitSTB(STB stmt);
        R visitSTW(STW stmt);
        R visitSTT(STT stmt);
        R visitSTO(STO stmt);
        R visitSTHT(STHT stmt);
        R visitSTCO(STCO stmt);
        R visitNEG(NEG stmt);
        R visitAND(AND stmt);
        R visitOR(OR stmt);
        R visitXOR(XOR stmt);
        R visitANDN(ANDN stmt);
        R visitORN(ORN stmt);
        R visitNAND(NAND stmt);
        R visitNOR(NOR stmt);
        R visitNXOR(NXOR stmt);
        R visitMUX(MUX stmt);
        R visitBDIF(BDIF stmt);
        R visitWDIF(WDIF stmt);
        R visitTDIF(TDIF stmt);
        R visitODIF(ODIF stmt);
        R visitSADD(SADD stmt);
        R visitMOR(MOR stmt);
        R visitMXOR(MXOR stmt);
        R visitSETH(SETH stmt);
        R visitSETMH(SETMH stmt);
        R visitSETML(SETML stmt);
        R visitSETL(SETL stmt);
        R visitINCH(INCH stmt);
        R visitINCMH(INCMH stmt);
        R visitINCML(INCML stmt);
        R visitINCL(INCL stmt);
        R visitORH(ORH stmt);
        R visitORMH(ORMH stmt);
        R visitORML(ORML stmt);
        R visitORL(ORL stmt);
        R visitANDNH(ANDNH stmt);
        R visitANDNMH(ANDNMH stmt);
        R visitANDNML(ANDNML stmt);
        R visitANDNL(ANDNL stmt);
        R visitSL(SL stmt);
        R visitSLU(SLU stmt);
        R visitSR(SR stmt);
        R visitSRU(SRU stmt);
        R visitCMP(CMP stmt);
        R visitCMPU(CMPU stmt);
        R visitCSN(CSN stmt);
        R visitCSZ(CSZ stmt);
        R visitCSP(CSP stmt);
        R visitCSOD(CSOD stmt);
        R visitCSNN(CSNN stmt);
        R visitCSNZ(CSNZ stmt);
        R visitCSNP(CSNP stmt);
        R visitCSEV(CSEV stmt);
        R visitZSN(ZSN stmt);
        R visitZSZ(ZSZ stmt);
        R visitZSP(ZSP stmt);
        R visitZSOD(ZSOD stmt);
        R visitZSNN(ZSNN stmt);
        R visitZSNZ(ZSNZ stmt);
        R visitZSNP(ZSNP stmt);
        R visitZSEV(ZSEV stmt);
        R visitBN(BN stmt);
        R visitBZ(BZ stmt);
        R visitBP(BP stmt);
        R visitBOD(BOD stmt);
        R visitBNN(BNN stmt);
        R visitBNZ(BNZ stmt);
        R visitBNP(BNP stmt);
        R visitBEV(BEV stmt);
        R visitPBN(PBN stmt);
        R visitPBZ(PBZ stmt);
        R visitPBP(PBP stmt);
        R visitPBOD(PBOD stmt);
        R visitPBNN(PBNN stmt);
        R visitPBNZ(PBNZ stmt);
        R visitPBNP(PBNP stmt);
        R visitPBEV(PBEV stmt);
        R visitGETA(GETA stmt);
        R visitGO(GO stmt);
        R visitFADD(FADD stmt);
        R visitFSUB(FSUB stmt);
        R visitFMUL(FMUL stmt);
        R visitFDIV(FDIV stmt);
        R visitFREM(FREM stmt);
        R visitFSQRT(FSQRT stmt);
        R visitFINT(FINT stmt);
        R visitFCMP(FCMP stmt);
        R visitFEQL(FEQL stmt);
        R visitFUN(FUN stmt);
        R visitFCMPE(FCMPE stmt);
        R visitFEQLE(FEQLE stmt);
        R visitFUNE(FUNE stmt);
        R visitLDSF(LDSF stmt);
        R visitSTSF(STSF stmt);
        R visitFIX(FIX stmt);
        R visitFIXU(FIXU stmt);
        R visitFLOT(FLOT stmt);
        R visitFLOTU(FLOTU stmt);
        R visitPUSHJ(PUSHJ stmt);
        R visitPUSHGO(PUSHGO stmt);
        R visitLDUNC(LDUNC stmt);
        R visitSTUNC(STUNC stmt);
        R visitPRELD(PRELD stmt);
        R visitPREGO(PREGO stmt);
        R visitPREST(PREST stmt);
        R visitCSWAP(CSWAP stmt);
        R visitSYNC(SYNC stmt);
        R visitRESUME(RESUME stmt);
        R visitGET(GET stmt);
        R visitPUT(PUT stmt);
        R visitSAVE(SAVE stmt);
        R visitUNSAVE(UNSAVE stmt);
        R visitLDVTS(LDVTS stmt);
        R visitSWYM(SWYM stmt);

        R visitJMP(JMP stmt);
    }

    //Need to do unsigned versions

    static class ADD extends Stmt {
        ADD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitADD(this);
        }
        void toPrint() {
            String output = "";
            output += "ADD ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SUB extends Stmt {
        SUB(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSUB(this);
        }
        void toPrint() {
            String output = "";
            output += "SUB ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class MUL extends Stmt {
        MUL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitMUL(this); }
        void toPrint() {
            String output = "";
            output += "MUL ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class DIV extends Stmt {
        DIV(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitDIV(this); }
        void toPrint() {
            String output = "";
            output += "DIV ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class TRIP extends Stmt {
        TRIP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitTRIP(this);
        }
        void toPrint() {
            String output = "";
            output += "TRIP ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDB extends Stmt {
        LDB(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLDB(this); }
        void toPrint() {
            String output = "";
            output += "LDB ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDW extends Stmt {
        LDW(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLDW(this); }
        void toPrint() {
            String output = "";
            output += "LDW ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDT extends Stmt {
        LDT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLDT(this); }
        void toPrint() {
            String output = "";
            output += "LDT ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDO extends Stmt {
        LDO(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLDO(this); }
        void toPrint() {
            String output = "";
            output += "LDO ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDHT extends Stmt {
        LDHT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLDHT(this); }
        void toPrint() {
            String output = "";
            output += "LDO ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDA extends Stmt {
        LDA(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLDA(this); }
        void toPrint() {
            String output = "";
            output += "LDA ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STB extends Stmt {
        STB(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSTB(this); }
        void toPrint() {
            String output = "";
            output += "STB ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STW extends Stmt {
        STW(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSTW(this); }
        void toPrint() {
            String output = "";
            output += "STW ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STT extends Stmt {
        STT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSTT(this); }
        void toPrint() {
            String output = "";
            output += "STT ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STO extends Stmt {
        STO(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSTO(this); }
        void toPrint() {
            String output = "";
            output += "STO ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STHT extends Stmt {
        STHT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSTHT(this); }
        void toPrint() {
            String output = "";
            output += "STHT ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STCO extends Stmt {
        STCO(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSTCO(this); }
        void toPrint() {
            String output = "";
            output += "STCO ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class NEG extends Stmt {
        NEG(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitNEG(this); }
        void toPrint() {
            String output = "";
            output += "NEG ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class AND extends Stmt {
        AND(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitAND(this); }
        void toPrint() {
            String output = "";
            output += "AND ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class OR extends Stmt {
        OR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitOR(this); }
        void toPrint() {
            String output = "";
            output += "OR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class XOR extends Stmt {
        XOR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitXOR(this); }
        void toPrint() {
            String output = "";
            output += "XOR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ANDN extends Stmt {
        ANDN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitANDN(this); }
        void toPrint() {
            String output = "";
            output += "ANDN ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ORN extends Stmt {
        ORN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitORN(this); }
        void toPrint() {
            String output = "";
            output += "ORN ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class NAND extends Stmt {
        NAND(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitNAND(this); }
        void toPrint() {
            String output = "";
            output += "NAND ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class NOR extends Stmt {
        NOR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitNOR(this); }
        void toPrint() {
            String output = "";
            output += "NOR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class NXOR extends Stmt {
        NXOR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitNXOR(this); }
        void toPrint() {
            String output = "";
            output += "NXOR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class MUX extends Stmt {
        MUX(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitMUX(this); }
        void toPrint() {
            String output = "";
            output += "MUX ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BDIF extends Stmt {
        BDIF(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitBDIF(this); }
        void toPrint() {
            String output = "";
            output += "BDIF ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class WDIF extends Stmt {
        WDIF(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitWDIF(this); }
        void toPrint() {
            String output = "";
            output += "WDIF ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class TDIF extends Stmt {
        TDIF(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitTDIF(this); }
        void toPrint() {
            String output = "";
            output += "TDIF ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ODIF extends Stmt {
        ODIF(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitODIF(this); }
        void toPrint() {
            String output = "";
            output += "ODIF ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SADD extends Stmt {
        SADD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSADD(this); }
        void toPrint() {
            String output = "";
            output += "SADD ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class MOR extends Stmt {
        MOR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitMOR(this); }
        void toPrint() {
            String output = "";
            output += "MOR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class MXOR extends Stmt {
        MXOR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitMXOR(this); }
        void toPrint() {
            String output = "";
            output += "MXOR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SETH extends Stmt {
        SETH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSETH(this); }
        void toPrint() {
            String output = "";
            output += "SETH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SETMH extends Stmt {
        SETMH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSETMH(this); }
        void toPrint() {
            String output = "";
            output += "SETMH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SETML extends Stmt {
        SETML(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSETML(this); }
        void toPrint() {
            String output = "";
            output += "SETML ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SETL extends Stmt {
        SETL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitSETL(this); }
        void toPrint() {
            String output = "";
            output += "SETL ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class INCH extends Stmt {
        INCH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitINCH(this); }
        void toPrint() {
            String output = "";
            output += "INCH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class INCMH extends Stmt {
        INCMH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitINCMH(this); }
        void toPrint() {
            String output = "";
            output += "INCMH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class INCML extends Stmt {
        INCML(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitINCML(this); }
        void toPrint() {
            String output = "";
            output += "INCML ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class INCL extends Stmt {
        INCL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitINCL(this); }
        void toPrint() {
            String output = "";
            output += "INCL ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ORH extends Stmt {
        ORH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitORH(this); }
        void toPrint() {
            String output = "";
            output += "ORH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ORMH extends Stmt {
        ORMH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitORMH(this); }
        void toPrint() {
            String output = "";
            output += "ORMH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ORML extends Stmt {
        ORML(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitORML(this); }
        void toPrint() {
            String output = "";
            output += "ORML ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ORL extends Stmt {
        ORL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitORL(this); }
        void toPrint() {
            String output = "";
            output += "ORL ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ANDNH extends Stmt {
        ANDNH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitANDNH(this); }
        void toPrint() {
            String output = "";
            output += "ANDNH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ANDNMH extends Stmt {
        ANDNMH(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitANDNMH(this); }
        void toPrint() {
            String output = "";
            output += "ANDNMH ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ANDNML extends Stmt {
        ANDNML(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitANDNML(this); }
        void toPrint() {
            String output = "";
            output += "ANDNML ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ANDNL extends Stmt {
        ANDNL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitANDNL(this); }
        void toPrint() {
            String output = "";
            output += "ANDNL ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class JMP extends Stmt {
        JMP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitJMP(this); }
        void toPrint() {
            String output = "";
            output += "JMP ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += "line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class IS extends Stmt {
        IS(Token key, Expr value, int line) {
            this.key = key;
            this.value = value;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitIS(this);
        }
        void toPrint() {
            String output = "";
            output += "IS ";
            output += this.key.lexeme + " ";
            output += "line: " + line;

            System.out.println(output);
        }

        final Token key;
        final Expr value;
        final int line;
    }

    static class SL extends Stmt {
        SL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSL(this);
        }
        void toPrint() {
            String output = "";
            output += "SL ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SLU extends Stmt {
        SLU(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSLU(this);
        }
        void toPrint() {
            String output = "";
            output += "SLU ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SR extends Stmt {
        SR(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSR(this);
        }
        void toPrint() {
            String output = "";
            output += "SR ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SRU extends Stmt {
        SRU(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSRU(this);
        }
        void toPrint() {
            String output = "";
            output += "SRU ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CMP extends Stmt {
        CMP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCMP(this);
        }
        void toPrint() {
            String output = "";
            output += "CMP ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CMPU extends Stmt {
        CMPU(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCMPU(this);
        }
        void toPrint() {
            String output = "";
            output += "CMPU ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSN extends Stmt {
        CSN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSN(this);
        }
        void toPrint() {
            String output = "";
            output += "CSN ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSZ extends Stmt {
        CSZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSZ(this);
        }
        void toPrint() {
            String output = "";
            output += "CSZ ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSP extends Stmt {
        CSP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSP(this);
        }
        void toPrint() {
            String output = "";
            output += "CSP ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSOD extends Stmt {
        CSOD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSOD(this);
        }
        void toPrint() {
            String output = "";
            output += "CSOD ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSNN extends Stmt {
        CSNN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSNN(this);
        }
        void toPrint() {
            String output = "";
            output += "CSNN ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSNZ extends Stmt {
        CSNZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSNZ(this);
        }
        void toPrint() {
            String output = "";
            output += "CSNZ ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSNP extends Stmt {
        CSNP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSNP(this);
        }
        void toPrint() {
            String output = "";
            output += "CSNP ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSEV extends Stmt {
        CSEV(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSEV(this);
        }
        void toPrint() {
            String output = "";
            output += "CSEV ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }

        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSN extends Stmt {
        ZSN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSN(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSZ extends Stmt {
        ZSZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSZ(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSZ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSP extends Stmt {
        ZSP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSP(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSOD extends Stmt {
        ZSOD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSOD(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSOD";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSNN extends Stmt {
        ZSNN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSNN(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSNN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSNZ extends Stmt {
        ZSNZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSNZ(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSNZ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSNP extends Stmt {
        ZSNP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSNP(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSNP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class ZSEV extends Stmt {
        ZSEV(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitZSEV(this);
        }
        void toPrint() {
            String output = "";
            output += "ZSEV";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BN extends Stmt {
        BN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBN(this);
        }
        void toPrint() {
            String output = "";
            output += "BN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BZ extends Stmt {
        BZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBZ(this);
        }
        void toPrint() {
            String output = "";
            output += "BZ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BP extends Stmt {
        BP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBP(this);
        }
        void toPrint() {
            String output = "";
            output += "BP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BOD extends Stmt {
        BOD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBOD(this);
        }
        void toPrint() {
            String output = "";
            output += "BOD";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BNN extends Stmt {
        BNN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBNN(this);
        }
        void toPrint() {
            String output = "";
            output += "BNN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BNZ extends Stmt {
        BNZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBNZ(this);
        }
        void toPrint() {
            String output = "";
            output += "BNZ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BNP extends Stmt {
        BNP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBNP(this);
        }
        void toPrint() {
            String output = "";
            output += "BNP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class BEV extends Stmt {
        BEV(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBEV(this);
        }
        void toPrint() {
            String output = "";
            output += "BEV";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBN extends Stmt {
        PBN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBN(this);
        }
        void toPrint() {
            String output = "";
            output += "PBN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBZ extends Stmt {
        PBZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBZ(this);
        }
        void toPrint() {
            String output = "";
            output += "PBZ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBP extends Stmt {
        PBP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBP(this);
        }
        void toPrint() {
            String output = "";
            output += "PBP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBOD extends Stmt {
        PBOD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBOD(this);
        }
        void toPrint() {
            String output = "";
            output += "PBOD";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBNZ extends Stmt {
        PBNZ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBNZ(this);
        }
        void toPrint() {
            String output = "";
            output += "PBNZ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBNP extends Stmt {
        PBNP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBNP(this);
        }
        void toPrint() {
            String output = "";
            output += "PBNP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBNN extends Stmt {
        PBNN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBNN(this);
        }
        void toPrint() {
            String output = "";
            output += "PBNN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PBEV extends Stmt {
        PBEV(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPBEV(this);
        }
        void toPrint() {
            String output = "";
            output += "PBEV";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class GETA extends Stmt {
        GETA(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGETA(this);
        }
        void toPrint() {
            String output = "";
            output += "GETA";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class GO extends Stmt {
        GO(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGO(this);
        }
        void toPrint() {
            String output = "";
            output += "GO";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FADD extends Stmt {
        FADD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFADD(this);
        }
        void toPrint() {
            String output = "";
            output += "FADD";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FSUB extends Stmt {
        FSUB(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFSUB(this);
        }
        void toPrint() {
            String output = "";
            output += "FSUB";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FMUL extends Stmt {
        FMUL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFMUL(this);
        }
        void toPrint() {
            String output = "";
            output += "FMUL";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FDIV extends Stmt {
        FDIV(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFDIV(this);
        }
        void toPrint() {
            String output = "";
            output += "FDIV";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FREM extends Stmt {
        FREM(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFREM(this);
        }
        void toPrint() {
            String output = "";
            output += "FREM";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FSQRT extends Stmt {
        FSQRT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFSQRT(this);
        }
        void toPrint() {
            String output = "";
            output += "FSQRT";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FINT extends Stmt {
        FINT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFINT(this);
        }
        void toPrint() {
            String output = "";
            output += "FINT";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FCMP extends Stmt {
        FCMP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFCMP(this);
        }
        void toPrint() {
            String output = "";
            output += "FCMP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FEQL extends Stmt {
        FEQL(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFEQL(this);
        }
        void toPrint() {
            String output = "";
            output += "FEQL";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FUN extends Stmt {
        FUN(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFUN(this);
        }
        void toPrint() {
            String output = "";
            output += "FUN";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FCMPE extends Stmt {
        FCMPE(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFCMPE(this);
        }
        void toPrint() {
            String output = "";
            output += "FCMPE";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FEQLE extends Stmt {
        FEQLE(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFEQLE(this);
        }
        void toPrint() {
            String output = "";
            output += "FEQLE";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FUNE extends Stmt {
        FUNE(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFUNE(this);
        }
        void toPrint() {
            String output = "";
            output += "FUNE";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDSF extends Stmt {
        LDSF(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLDSF(this);
        }
        void toPrint() {
            String output = "";
            output += "LDSF";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STSF extends Stmt {
        STSF(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSTSF(this);
        }
        void toPrint() {
            String output = "";
            output += "STSF";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FIX extends Stmt {
        FIX(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFIX(this);
        }
        void toPrint() {
            String output = "";
            output += "FIX";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FIXU extends Stmt {
        FIXU(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFIXU(this);
        }
        void toPrint() {
            String output = "";
            output += "FIXU";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FLOT extends Stmt {
        FLOT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFLOT(this);
        }
        void toPrint() {
            String output = "";
            output += "FLOT";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class FLOTU extends Stmt {
        FLOTU(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFLOTU(this);
        }
        void toPrint() {
            String output = "";
            output += "FLOTU";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PUSHJ extends Stmt {
        PUSHJ(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPUSHJ(this);
        }
        void toPrint() {
            String output = "";
            output += "PUSHJ";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PUSHGO extends Stmt {
        PUSHGO(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPUSHGO(this);
        }
        void toPrint() {
            String output = "";
            output += "PUSHGO";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDUNC extends Stmt {
        LDUNC(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLDUNC(this);
        }
        void toPrint() {
            String output = "";
            output += "LDUNC";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class STUNC extends Stmt {
        STUNC(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSTUNC(this);
        }
        void toPrint() {
            String output = "";
            output += "STUNC";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PRELD extends Stmt {
        PRELD(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPRELD(this);
        }
        void toPrint() {
            String output = "";
            output += "PRELD";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PREGO extends Stmt {
        PREGO(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPREGO(this);
        }
        void toPrint() {
            String output = "";
            output += "PREGO";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PREST extends Stmt {
        PREST(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPREST(this);
        }
        void toPrint() {
            String output = "";
            output += "PREST";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class CSWAP extends Stmt {
        CSWAP(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCSWAP(this);
        }
        void toPrint() {
            String output = "";
            output += "CSWAP";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SYNC extends Stmt {
        SYNC(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSYNC(this);
        }
        void toPrint() {
            String output = "";
            output += "SYNC";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class RESUME extends Stmt {
        RESUME(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitRESUME(this);
        }
        void toPrint() {
            String output = "";
            output += "RESUME";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class GET extends Stmt {
        GET(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGET(this);
        }
        void toPrint() {
            String output = "";
            output += "GET";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class PUT extends Stmt {
        PUT(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPUT(this);
        }
        void toPrint() {
            String output = "";
            output += "PUT";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SAVE extends Stmt {
        SAVE(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSAVE(this);
        }
        void toPrint() {
            String output = "";
            output += "SAVE";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class UNSAVE extends Stmt {
        UNSAVE(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitUNSAVE(this);
        }
        void toPrint() {
            String output = "";
            output += "UNSAVE";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class LDVTS extends Stmt {
        LDVTS(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLDVTS(this);
        }
        void toPrint() {
            String output = "";
            output += "LDVTS";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }

    static class SWYM extends Stmt {
        SWYM(Expr label, List<Expr> args, int line) {
            this.label = label;
            this.args = args;
            this.line = line;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitSWYM(this);
        }
        void toPrint() {
            String output = "";
            output += "SWYM";

            for(Expr arg : args) {
                output += arg.getValue() + " ";
            }

            output += " line: " + line;

            System.out.println(output);
        }


        final Expr label;
        final List<Expr> args;
        final int line;
    }



    abstract <R> R accept(Visitor<R> visitor);
    abstract void toPrint();
}
