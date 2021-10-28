package com.MMIX;

import java.math.BigInteger;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    final Map<String, Object> values = new HashMap<>();

    final Map<String, Integer> labels = new HashMap<>();
    final List<Token> locals = new ArrayList<>();

    private int startPC;
    private int pcOffset;

    private byte[] memory = new byte[1024];
    private byte[][] registers = new byte[256][8];
    private byte[][] special = new byte[32][8];

    private Stack<byte[]> rStack = new Stack<>();

    long l = 0;
    int g = 255;

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

    public int decG() {
        return g--;
    }

    public void setL(int index) {
        l = index + 1;
    }

    public void incPcOffset() {
        pcOffset++;
    }

    public int getPcOffset() {
        return this.pcOffset;
    }

    public void setStartPC(int startPC) {
        this.startPC = startPC;
    }

    public int getStartPC() {
        return this.startPC;
    }

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

        if(index > l && index < g) {
            setL(index);
        }

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

    public void storeSpecial(int index, long value) {
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
            MMIX.environment.special[(int)index][i] = num[i];
        }
    }

    public long loadSpecial(int index) {
        byte[] num = this.special[index];

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

    public void pushReg() {

        for(int i = 0; i < l; i++) {
            byte[] num = new byte[8];
            for(int j = 0; j < 8; j++)
                num[j] = registers[i][j];

            rStack.push(num);
            clearReg(i);
        }

        byte[] lReg = new byte[] {
                (byte) ((l >> 56) & 0xFFL),
                (byte) ((l >> 48) & 0xFFL),
                (byte) ((l >> 40) & 0xFFL),
                (byte) ((l >> 32) & 0xFFL),
                (byte) ((l >> 24) & 0xFFL),
                (byte) ((l >> 16) & 0xFFL),
                (byte) ((l >> 8) & 0xFFL),
                (byte) ((l) & 0xFFL),
        };

        rStack.push(lReg);
    }

    public void popReg() {

        byte[] lReg = rStack.pop();

        l =((lReg[0] & 0xFFL) << 56) |
                ((lReg[1] & 0xFFL) << 48) |
                ((lReg[2] & 0xFFL) << 40) |
                ((lReg[3] & 0xFFL) << 32) |
                ((lReg[4] & 0xFFL) << 24) |
                ((lReg[5] & 0xFFL) << 16) |
                ((lReg[6] & 0xFFL) <<  8) |
                ((lReg[7] & 0xFFL));


        for(int i = rStack.size() - 1; !rStack.isEmpty(); i--) {
            byte[] num = rStack.pop();
            registers[i] = num;
        }
    }

    void clearReg(int n) {
        for(int i = 0; i < 8; i++) {
            registers[n][i] = 0;
        }
    }
}