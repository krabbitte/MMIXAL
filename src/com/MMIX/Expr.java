package com.MMIX;

abstract class Expr {
    interface Visitor<R> {
        R visitBinaryExpr(Binary expr);
        R visitGroupingExpr(Grouping expr);
        R visitLiteralExpr(Literal expr);
        R visitUnaryExpr(Unary expr);
        R visitLabelExpr(Label expr);
        R visitVariableExpr(Variable expr);
    }

    static class Binary extends Expr {
        Binary(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitBinaryExpr(this); }
        String getValue() { return "Binary";}

        final Expr left;
        final Token operator;
        final Expr right;
    }

    static class Grouping extends Expr {
        Grouping(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitGroupingExpr(this); }
        String getValue() { return "Grouping";}

        final Expr expression;
    }

    static class Literal extends Expr {
        Literal(Object value) {
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLiteralExpr(this); }
        String getValue() { return this.value.getClass().toString();}

        final Object value;
    }

    static class Label extends Expr {
        Label(Token label) {
            this.label = label;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitLabelExpr(this); }
        String getValue() { return this.label.lexeme;}

        final Token label;
    }

    static class Unary extends Expr {

        Unary(Token operator, Expr right) {
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) { return visitor.visitUnaryExpr(this); }
        String getValue() { return "Unary";}

        final Token operator;
        final Expr right;
    }

    static class Variable extends Expr {
        Variable(Token name) {
            this.name = name;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitVariableExpr(this);
        }
        String getValue() { return this.name.lexeme; }

        final Token name;
    }

    abstract <R> R accept(Visitor<R> visitor);
    abstract String getValue();
}
