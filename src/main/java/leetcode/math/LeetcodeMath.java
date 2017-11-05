package leetcode.math;

import java.math.BigDecimal;

/**
 * Created by dynam on 2017/1/22.
 */
public class LeetcodeMath {

    /**
     * Reverse digits of an integer.
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     * <p>
     * The input is assumed to be a 32-bit signed integer.
     * Your function should return 0 when the reversed integer overflows.
     */
    public int reverse(int x) {

        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x /= 10;
            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) ans;

    }

    public int reverseBetter(int x) {
        int ans = 0;

        while (x != 0) {
            int tail = x % 10;
            int temp = ans * 10 + tail;
            if ((temp - tail) / 10 != ans) {
                return 0;
            }
            ans = temp;
            x /= 10;
        }

        return ans;
    }


    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }


    /**
     * 9. Palindrome Number
     * <p>
     * Determine whether an integer is a palindrome. Do this without extra space.
     */
    public boolean isPalindrome(int x) {

        String s = String.valueOf(x);

        if (s.length() == 1) {
            return true;
        }

        int len = s.length();
        int round = len / 2;

        for (int i = 0; i < round; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }

        return true;
    }


    public void testMain() {

    }

}
