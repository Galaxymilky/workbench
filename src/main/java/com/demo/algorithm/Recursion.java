package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dynam on 2017/2/12.
 */
public class Recursion {

    /**
     * Fibonacci sequence
     */
    public long fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * do not use Recursion, 0ms
     */
    public long fibonacciQuick(int n) {
        List<Long> list = new ArrayList<Long>(n);
        list.add(0, (long) 0);
        list.add(1, (long) 1);
        list.add(2, (long) 1);
        long res;
        for (int i = 3; i <= n; i++) {
            res = list.get(i - 1) + list.get(i - 2);
            list.add(i, res);
        }
        return list.get(n);
    }

}
