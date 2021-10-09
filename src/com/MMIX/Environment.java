package com.MMIX;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    final Map<String, Object> values = new HashMap<>();

    final Map<String, Integer> labels = new HashMap<>();
    final List<Token> locals = new ArrayList<>();

    private byte[] memory = new byte[1024];
    private byte[][] registers = new byte[256][8];
    private byte[][] special = new byte[32][];

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

    public void storeMem(int baseA, int offset, long value, char type) {
        int pos, bytes;

        if (type == 'O') {
            bytes = 8;
        } else if (type == 'T') {
            bytes = 4;
        } else if (type == 'W') {
            bytes = 2;
        } else {
            bytes = 1;
        }

        pos = (baseA + offset) % bytes;

        byte[] num = new byte[] {
                (byte) ((value >> 56) & 0xFFL),
                (byte) ((value >> 48) & 0xFFL),
                (byte) ((value >> 40) & 0xFFL),
                (byte) ((value >> 32) & 0xFFL),
                (byte) ((value >> 24) & 0xFFL),
                (byte) ((value >> 16) & 0xFFL),
                (byte) ((value >> 8) & 0xFFL),
                (byte) ((value) & 0xFFL),
        };

        for(int i = pos, j = 0; i >= 0; i--, j++) {
            memory[baseA + offset - j] = num[(8-bytes) + i];
        }

        for(int i = pos + 1, j = 1; i < bytes; i++, j++) {
            memory[baseA + offset + j] = num[(8-bytes) + i];
        }
    }

    public long loadMem(int baseA, int offset, char type) {
        int pos, bytes;
        byte[] num = new byte[8];

        if(type == 'O') {
            bytes = 8;
        } else if (type == 'T') {
            bytes = 4;
        } else if (type == 'W') {
            bytes = 2;
        } else {
            bytes = 1;
        }

        pos = (baseA + offset) % bytes;

        for(int i = pos, j = 0; i >= 0; i--, j++) {
            num[(8-bytes) + i] = memory[baseA + offset - j];
        }


        for(int i = pos + 1, j = 1; i < bytes; i++, j++) {
            num[(8-bytes) + i] = memory[baseA + offset + j];
        }

        long x =((num[0] & 0xFFL) << 56) |
                ((num[1] & 0xFFL) << 48) |
                ((num[2] & 0xFFL) << 40) |
                ((num[3] & 0xFFL) << 32) |
                ((num[4] & 0xFFL) << 24) |
                ((num[5] & 0xFFL) << 16) |
                ((num[6] & 0xFFL) <<  8) |
                ((num[7] & 0xFFL));

        return x;
    }

    public void storeReg(int index, long value) {
        byte[] num = new byte[] {
                (byte) ((value >> 56) & 0xFFL),
                (byte) ((value >> 48) & 0xFFL),
                (byte) ((value >> 40) & 0xFFL),
                (byte) ((value >> 32) & 0xFFL),
                (byte) ((value >> 24) & 0xFFL),
                (byte) ((value >> 16) & 0xFFL),
                (byte) ((value >> 8) & 0xFFL),
                (byte) ((value) & 0xFFL),
        };

        for (int i = 0; i < num.length; i++) {
            MMIX.environment.registers[(int)index][i] = num[i];
        }
    }

    public long loadReg(int address) {
        byte[] num = this.registers[address];

        long x =((num[0] & 0xFFL) << 56) |
                ((num[1] & 0xFFL) << 48) |
                ((num[2] & 0xFFL) << 40) |
                ((num[3] & 0xFFL) << 32) |
                ((num[4] & 0xFFL) << 24) |
                ((num[5] & 0xFFL) << 16) |
                ((num[6] & 0xFFL) <<  8) |
                ((num[7] & 0xFFL));

        return x;
    }
}