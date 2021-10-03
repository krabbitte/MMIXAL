package com.MMIX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    final Map<String, Object> values = new HashMap<>();

    final Map<String, Integer> labels = new HashMap<>();
    final List<Token> locals = new ArrayList<>();

    private byte[][] memory = new byte[512][8];
    private byte[][] special = new byte[256][8];

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

    public void storeMem(int index, int value) {
        byte [] num = new byte[] {
                (byte)((value >> 24) & 0xff),
                (byte)((value >> 16) & 0xff),
                (byte)((value >> 8) & 0xff),
                (byte)(value & 0xff),
        };

        for (int i = 0; i < num.length; i++) {
            MMIX.environment.memory[(int) index][i] = num[i];
        }
    }

    public void storeReg(int index, int value) {
        byte [] num = new byte[] {
                (byte)((value >> 24) & 0xff),
                (byte)((value >> 16) & 0xff),
                (byte)((value >> 8) & 0xff),
                (byte)(value & 0xff),
        };

        for (int i = 0; i < num.length; i++) {
            MMIX.environment.special[(int)index][i] = num[i];
        }
    }

    public int loadMem(int index) {

        byte[] num = this.memory[index];

        int x = (num[0] & 0xff) << 24 |
                (num[1] & 0xff) << 16 |
                (num[2] & 0xff) << 8  |
                (num[3] & 0xff);

        return x;
    }

    public int loadReg(int index) {

        byte[] num = this.special[index];

        int x = (num[0] & 0xff) << 24 |
                (num[1] & 0xff) << 16 |
                (num[2] & 0xff) << 8  |
                (num[3] & 0xff);

        return x;
    }
}