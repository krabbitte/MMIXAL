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
    public  Void visitDIV(Stmt.DIV stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("DIV done");

        return null;
    }

    @Override
    public  Void visitLDB(Stmt.LDB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDB done");

        return null;
    }

    @Override
    public  Void visitLDW(Stmt.LDW stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDW done");

        return null;
    }

    @Override
    public  Void visitLDT(Stmt.LDT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDT done");

        return null;
    }

    @Override
    public  Void visitLDO(Stmt.LDO stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDO done");

        return null;
    }

    @Override
    public  Void visitLDHT(Stmt.LDHT stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("LDHT done");

        return null;
    }

    @Override
    public  Void visitLDA(Stmt.LDA stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));

        System.out.println("LDA done");

        return null;
    }

    @Override
    public  Void visitSTB(Stmt.STB stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STB done");

        return null;
    }

    @Override
    public  Void visitSTW(Stmt.STW stmt) {
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("STW done");

        return null;
    }

    @Override
    public  Void visitSTT(Stmt.STT stmt) {
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
        Object arg1 = evaluate(stmt.args.get(0));
        Object arg2 = evaluate(stmt.args.get(1));
        Object arg3 = evaluate(stmt.args.get(2));

        System.out.println("TRIP");
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
