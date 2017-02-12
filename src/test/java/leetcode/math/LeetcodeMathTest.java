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
    }

}