package leetcode.math;

/**
 * Created by dynam on 2017/1/22.
 */
public class LeetcodeMath {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        while (--n >= 1) {
            return x * myPow(x, n);
        }

        return x;
    }


    public void testMain(){

    }

}
