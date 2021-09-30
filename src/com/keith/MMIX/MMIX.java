package com.keith.MMIX;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.List;

public class MMIX {
    static boolean hadError = false;
    static boolean hadRuntimeError = false;
    static Environment environment = new Environment();

    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            runFile(args[0]);
        } else {
            System.out.println("Usage: MMIX [file]");
            System.exit(64);
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) System.exit(65);
        if (hadRuntimeError) System.exit(70);
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        /*
        for(Token token : tokens) {
            if(token.type != TokenType.EOL) {
                System.out.println(token.lexeme + " " + token.line);
            } else {
                System.out.println(token.type.toString());
            }
        }
         */

        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        /*
        System.out.println("\n");
        for(Stmt statement : statements) {
            statement.toPrint();
        }
         */

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(statements);
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }
}
