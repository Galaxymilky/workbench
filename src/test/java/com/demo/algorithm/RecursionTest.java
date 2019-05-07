package com.demo.algorithm;

import org.junit.Test;

/**
 * Created by dynam on 2017/2/12.
 */
public class RecursionTest {

    private static Recursion recursion = new Recursion();

    @Test
    public void fibonacciSeq() throws Exception {
        int n = 42;
        System.out.println(recursion.fibonacci(n));
    }


    @Test
    public void fibonacciSeq2() throws Exception {
        int n = 50;
        System.out.println(recursion.fibonacciQuick(n));
    }
}