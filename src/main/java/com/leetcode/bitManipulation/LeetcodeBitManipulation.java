package com.leetcode.bitManipulation;

/**
 * Created by dynamicniu on 2018/3/1.
 */
public class LeetcodeBitManipulation {
    public static void main(String[] args) {
        LeetcodeBitManipulation lc = new LeetcodeBitManipulation();
        int x = lc.countPrimeSetBits(10, 15);
        System.out.println(x);
    }

    public int countPrimeSetBits(int L, int R) {
        int count = 0;
        while (L <= R) {
            if (isNumValid(L++)) {
                count++;
            }
        }
        return count;
    }

    private boolean isNumValid(int i) {
        String s = Integer.toBinaryString(i);
        if ("".equals(s) || s == null) {
            return false;
        }

        int count = 0;
        for (int k = 0; k < s.length(); k++) {
            if (s.charAt(k) == '1') {
                count++;
            }
        }

        System.out.println(s + " x " + i + " x " + count);

        for (int k = 2; k < count; k++) {
            if (count % k == 0) {
                return false;
            }
        }
        return true;
    }
}
