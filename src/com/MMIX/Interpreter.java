package com.MMIX;

import java.util.BitSet;
import java.util.List;
import static com.MMIX.TokenType.*;

class Interpreter implements Expr.Visitor<Object>, Stmt.Visitor<Void> {

    private int pc = 0;
    private boolean halt = false;

    void interpret(List<Stmt> statements) {
        pc = MMIX.environment.getStartPC();
        try {
            while(!halt) {
                execute(statements.get(pc));
                pc++;
            }
        } catch (RuntimeError error) {
            MMIX.runtimeError(error);
        }
    }

    // Done
    @Override
    public Void visitADD(Stmt.ADD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            throw new RuntimeError(new Token(REG, "error", null, stmt.line), "not a register");
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y + z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ADD: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitSUB(Stmt.SUB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "Y is not a register");
            return null;
        }


        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y - z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("SUB: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitMUL(Stmt.MUL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "Y is not a register");
            return null;
        }


        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y * z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("MUL: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitDIV(Stmt.DIV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "Y is not a register");
            return null;
        }


        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y/z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("DIV: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitLDB(Stmt.LDB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = Long.valueOf((int)arg2);
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = Long.valueOf((int)arg3);
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = MMIX.environment.loadMem((int)y, (int)z, 'B');

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("LDB: R[" + index + "] <- M[" + (y+z) + "] ~ " + MMIX.environment.loadReg(index));
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitLDW(Stmt.LDW stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = MMIX.environment.loadMem((int)y, (int)z, 'W');

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("LDW: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitLDT(Stmt.LDT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = MMIX.environment.loadMem((int)y, (int)z, 'T');

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("LDT: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitLDO(Stmt.LDO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = MMIX.environment.loadMem((int)y, (int)z, 'O');

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("LDO: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitLDHT(Stmt.LDHT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = MMIX.environment.loadMem((int)y, (int)z, 'T');

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("LDHT: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitLDA(Stmt.LDA stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y + z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("LDA: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitSTB(Stmt.STB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        long index = (y+z) % 2064;

        if(arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)(((Token)arg1).literal));
            MMIX.environment.storeMem((int)y, (int)z, x, 'B');
            System.out.println("STB: M[" + (index) + "] <- R[" + ((Token) arg1).literal + "] " + MMIX.environment.loadMem((int)y, (int)z, 'B'));
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitSTW(Stmt.STW stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)(((Token)arg1).literal));
            MMIX.environment.storeMem((int)y, (int)z, x, 'W');
            System.out.println("STW: M[" + (y+z) + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitSTT(Stmt.STT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)(((Token)arg1).literal));
            MMIX.environment.storeMem((int)y, (int)z, x, 'T');
            System.out.println("STT: M[" + (y+z) + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitSTO(Stmt.STO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)(((Token)arg1).literal));
            MMIX.environment.storeMem((int)y, (int)z, x, 'O');
            System.out.println("STO: M[" + (y+z) + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitSTHT(Stmt.STHT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg1 instanceof Integer) {
            x = (int) arg1;
        } else if (arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)(((Token)arg1).literal));
        } else {
            x = 0;
        }

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)x);
            MMIX.environment.storeMem((int)y, (int)z, x, 'T');
            System.out.println("STHT: M[" + (y+z) + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Partially Done
    @Override
    public Void visitSTCO(Stmt.STCO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg1 instanceof Integer) {
            x = (int) arg1;
        } else if (arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)(((Token)arg1).literal));
        } else {
            x = 0;
        }

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(arg1 instanceof Token) {
            x = MMIX.environment.loadReg((int)x);
            MMIX.environment.storeMem((int)y, (int)z, x, 'O');
            System.out.println("STB: M[" + (y+z) + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitNEG(Stmt.NEG stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        long x,y;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "Y not a register");
            return null;
        }

        x = ~y;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("NEG: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitAND(Stmt.AND stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y & z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("AND: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitOR(Stmt.OR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y | z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("OR: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitXOR(Stmt.XOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y ^ z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("XOR: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitANDN(Stmt.ANDN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y & ~z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ANDN: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitORN(Stmt.ORN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = y | ~z;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ORN: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitNAND(Stmt.NAND stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = ~(y & z);

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("NAND: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitNOR(Stmt.NOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = ~(y | z);

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("NOR: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitNXOR(Stmt.NXOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = ~(y ^ z);

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("NXOR: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Come Back
    @Override
    public Void visitMUX(Stmt.MUX stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = 0;

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("MUX: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Come Back
    @Override
    public Void visitBDIF(Stmt.BDIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("BDIF done");

        return null;
    }

    // Come Back
    @Override
    public Void visitWDIF(Stmt.WDIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("WDIF done");

        return null;
    }

    // Come Back
    @Override
    public Void visitTDIF(Stmt.TDIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("TDIF done");

        return null;
    }

    // Come Back
    @Override
    public Void visitODIF(Stmt.ODIF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ODIF done");

        return null;
    }

    // Done
    @Override
    public Void visitSADD(Stmt.SADD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            MMIX.error(stmt.line, "y not a register");
            return null;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        x = (y & ~z);

        System.out.println(~z);

        BitSet bit = BitSet.valueOf(new long[]{x});

        x = bit.cardinality();

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("SADD: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        System.out.println("SADD done");

        return null;
    }

    // Come Back
    @Override
    public Void visitMOR(Stmt.MOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("MOR done");

        return null;
    }

    // Come Back
    @Override
    public Void visitMXOR(Stmt.MXOR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("MXOR done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSETH(Stmt.SETH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSETMH(Stmt.SETMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETMH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSETML(Stmt.SETML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SETML done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSETL(Stmt.SETL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        long x,y;


        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        x = (int)(((Token)arg1).literal);

        if(arg1 instanceof Token) {
            MMIX.environment.storeReg((int)x, y);
            System.out.println("SETL: R[" + x + "] <- " + y);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Come Back
    @Override
    public Void visitINCH(Stmt.INCH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitINCMH(Stmt.INCMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCMH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitINCML(Stmt.INCML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCML done");

        return null;
    }

    // Come Back
    @Override
    public Void visitINCL(Stmt.INCL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("INCL done");

        return null;
    }

    // Come Back
    @Override
    public Void visitORH(Stmt.ORH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitORMH(Stmt.ORMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORMH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitORML(Stmt.ORML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORML done");

        return null;
    }

    // Come Back
    @Override
    public Void visitORL(Stmt.ORL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ORL done");

        return null;
    }

    // Come Back
    @Override
    public Void visitANDNH(Stmt.ANDNH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitANDNMH(Stmt.ANDNMH stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNMH done");

        return null;
    }

    // Come Back
    @Override
    public Void visitANDNML(Stmt.ANDNML stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNML done");

        return null;
    }

    // Come Back
    @Override
    public Void visitANDNL(Stmt.ANDNL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("ANDNL done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSL(Stmt.SL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SL done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSLU(Stmt.SLU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SLU done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSR(Stmt.SR stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SR done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSRU(Stmt.SRU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SRU done");

        return null;
    }

    // Done
    @Override
    public Void visitCMP(Stmt.CMP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y > z) {
            x = 1;
        } else if (y == z) {
            x = 0;
        } else {
            x = -1;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CMP: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCMPU(Stmt.CMPU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        y = Math.abs(y);
        z = Math.abs(z);

        if(y > z) {
            x = 1;
        } else if (y == z) {
            x = 0;
        } else {
            x = -1;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CMPU: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSN(Stmt.CSN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y < 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSN: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSZ(Stmt.CSZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            return null;
        }

        if(y == 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSZ: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSP(Stmt.CSP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y > 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSP: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSOD(Stmt.CSOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y % 2 != 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSOD: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSNN(Stmt.CSNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y >= 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSNN: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSNZ(Stmt.CSNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y != 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSNZ: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSNP(Stmt.CSNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y <= 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSNP: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitCSEV(Stmt.CSEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y % 2 == 0) {
            x = z;
        } else {
            return null;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("CSEV: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSN(Stmt.ZSN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y < 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSN: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSZ(Stmt.ZSZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y == 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSZ: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSP(Stmt.ZSP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y > 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSP: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSOD(Stmt.ZSOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y % 2 != 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSOD: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSNN(Stmt.ZSNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y >= 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSNN: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSNZ(Stmt.ZSNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y != 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSNZ: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSNP(Stmt.ZSNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y <= 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSNP: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitZSEV(Stmt.ZSEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        long x,y,z;

        if(arg2 instanceof Integer) {
            y = (int) arg2;
        } else if (arg2 instanceof Token) {
            y = MMIX.environment.loadReg((int)(((Token)arg2).literal));
        } else {
            y = 0;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = MMIX.environment.loadReg((int)(((Token)arg3).literal));
        } else {
            z = 0;
        }

        if(y % 2 == 0) {
            x = z;
        } else {
            x = 0;
        }

        if(arg1 instanceof Token) {
            int index = (int)(((Token)arg1).literal);
            MMIX.environment.storeReg(index, x);
            System.out.println("ZSeV: R[" + index + "] <- " + x);
        } else {
            MMIX.error(stmt.line, "Not a register");
        }

        return null;
    }

    // Done
    @Override
    public Void visitBN(Stmt.BN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x < 0) {
            System.out.println("BN: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BN: Branch not taken");
        }

        return null;
    }

    // Done
    @Override
    public Void visitBZ(Stmt.BZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x == 0) {
            System.out.println("BZ: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BZ: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Done
    @Override
    public Void visitBP(Stmt.BP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x > 0) {
            System.out.println("BP: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BP: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Done
    @Override
    public Void visitBOD(Stmt.BOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x % 2 == 0) {
            System.out.println("BOD: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BOD: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Done
    @Override
    public Void visitBNN(Stmt.BNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x != 0) {
            System.out.println("BNN: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BNN: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Done
    @Override
    public Void visitBNZ(Stmt.BNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x != 0) {
            System.out.println("BP: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BP: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Done
    @Override
    public Void visitBNP(Stmt.BNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x <= 0) {
            System.out.println("BNP: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BNP: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Done
    @Override
    public Void visitBEV(Stmt.BEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        int line = 0;
        long x = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            line = (int)arg2;
        } else if(arg2 instanceof Token) {
            line = (int)((Token)arg2).literal;
        }

        x = MMIX.environment.loadReg((int)x);

        if(x % 2 == 0) {
            System.out.println("BEV: Branch taken - pc <- " + line);
            if(line != 0) {
                this.pc = line - MMIX.environment.getPcOffset();
            }
        } else {
            System.out.println("BEV: Branch not taken - pc <- " + line);
        }

        return null;
    }

    // Come Back
    @Override
    public Void visitPBN(Stmt.PBN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBN done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBZ(Stmt.PBZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBZ done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBP(Stmt.PBP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBP done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBOD(Stmt.PBOD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBOD done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBNN(Stmt.PBNN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBNN done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBNZ(Stmt.PBNZ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBNZ done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBNP(Stmt.PBNP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBNP done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPBEV(Stmt.PBEV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PBEV done");

        return null;
    }

    // Come Back
    @Override
    public Void visitGETA(Stmt.GETA stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("GETA done");

        return null;
    }

    // Come Back
    @Override
    public Void visitGO(Stmt.GO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("GO done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFADD(Stmt.FADD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FADD done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFSUB(Stmt.FSUB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FSUB done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFMUL(Stmt.FMUL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FMUL done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFDIV(Stmt.FDIV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FDIV done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFREM(Stmt.FREM stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FREM done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFSQRT(Stmt.FSQRT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FSQRT done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFINT(Stmt.FINT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FINT done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFCMP(Stmt.FCMP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FCMP done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFEQL(Stmt.FEQL stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FEQL done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFUN(Stmt.FUN stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FUN done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFCMPE(Stmt.FCMPE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FCMPE done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFEQLE(Stmt.FEQLE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FEQLE done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFUNE(Stmt.FUNE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FUNE done");

        return null;
    }

    // Come Back
    @Override
    public Void visitLDSF(Stmt.LDSF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDSF done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSTSF(Stmt.STSF stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STSF done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFIX(Stmt.FIX stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FIX done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFIXU(Stmt.FIXU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FIXU done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFLOT(Stmt.FLOT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FLOT done");

        return null;
    }

    // Come Back
    @Override
    public Void visitFLOTU(Stmt.FLOTU stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("FLOTU done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPUSHJ(Stmt.PUSHJ stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PUSHJ done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPUSHGO(Stmt.PUSHGO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        int line = 0;
        long x, y, z;
        x = y = z = 0;

        if(arg1 instanceof Token) {
            x = (int)((Token)arg1).literal;
        } else {
            MMIX.error(stmt.line, "Not a register");
            return null;
        }

        if(arg2 instanceof Integer) {
            y = (int)arg2;
        } else if(arg2 instanceof Token) {
            y = (int)((Token)arg2).literal;
        }

        if(arg3 instanceof Integer) {
            z = (int)arg3;
        } else if(arg3 instanceof Token) {
            z = (int)((Token)arg3).literal;
        }

        x = MMIX.environment.loadReg((int)x);
        line = (int)y + (int)z;

        if(x < 0) {
            System.out.println("PUSHGO: ");
            if(line != 0) {
                this.pc = line - 2;
            }
        } else {
            System.out.println("BN: Branch not taken");
        }

        return null;
    }

    // Come Back
    @Override
    public Void visitLDUNC(Stmt.LDUNC stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDUNC done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSTUNC(Stmt.STUNC stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STUNC done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPRELD(Stmt.PRELD stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PRELD done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPREGO(Stmt.PREGO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PREGO done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPREST(Stmt.PREST stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PREST done");

        return null;
    }

    // Come Back
    @Override
    public Void visitCSWAP(Stmt.CSWAP stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("CSWAP done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSYNC(Stmt.SYNC stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SYNC done");

        return null;
    }

    // Come Back
    @Override
    public Void visitRESUME(Stmt.RESUME stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("RESUME done");

        return null;
    }

    // Come Back
    @Override
    public Void visitGET(Stmt.GET stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("GET done");

        return null;
    }

    // Come Back
    @Override
    public Void visitPUT(Stmt.PUT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("PUT done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSAVE(Stmt.SAVE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SAVE done");

        return null;
    }

    // Come Back
    @Override
    public Void visitUNSAVE(Stmt.UNSAVE stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("UNSAVE done");

        return null;
    }

    // Come Back
    @Override
    public Void visitLDVTS(Stmt.LDVTS stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDVTS done");

        return null;
    }

    // Come Back
    @Override
    public Void visitSWYM(Stmt.SWYM stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("SWYM done");

        return null;
    }

    // Done
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
            this.pc = line - MMIX.environment.getPcOffset() - 2;
        }

        return null;
    }

    // Done
    @Override
    public Void visitIS(Stmt.IS stmt) {

        Object value = evaluate(stmt.value);
        Object key = stmt.key.lexeme;

        MMIX.environment.values.put((String)key, value);

        return null;
    }

    // Partially Done
    @Override
    public Void visitTRIP(Stmt.TRIP stmt) {

        System.out.println("HALT");
        halt = true;

        return null;
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {

        Object value = null;

        if(expr.name.type == REG) {
            value = expr.name;
        }
        else if (MMIX.environment.values.containsKey(expr.name.lexeme))
        {
            value = MMIX.environment.values.get(expr.name.lexeme);
        }
        else if (expr.name.type.ordinal() >= 29 && expr.name.type.ordinal() <= 48)
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

        } else {
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
