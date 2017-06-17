package leetcode.math;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dynam on 2017/1/22.
 */
public class LeetcodeMathTest {
    LeetcodeMath lm = new LeetcodeMath();

    @Test
    public void myPow() throws Exception {
        double x = 8.88023;
        int n = 3;
        System.out.println(lm.myPow(x, n));
        System.out.println(8.88023 * 8.88023 * 8.88023);

        x = 1.00001;
        n = 123456;
        System.out.println(lm.myPow(x, n));

    }

    @Test
    public void reverse() {
        int x = 123456;
        int x1 = -123456789;

        System.out.println(lm.reverseBetter(x));
        System.out.println(lm.reverse(x1));

    }


}