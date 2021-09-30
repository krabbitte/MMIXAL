package com.keith.MMIX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    final Map<String, Object> values = new HashMap<>();

    final Map<String, Integer> labels = new HashMap<>();
    final List<Token> locals = new ArrayList<>();

    byte[][] memory = new byte[2^9][8];
    byte[][] special = new byte[2^8][8];

    Object get(Token name) {
        if(values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }
        throw new RuntimeError(name, "undefined variable '" + name.lexeme + "'.");
    }

    void assign(Token name, Object value) {
        if(values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable" + name.lexeme + "'.");
    }

    void define(String name, Object value) { values.put(name,value); }
}
