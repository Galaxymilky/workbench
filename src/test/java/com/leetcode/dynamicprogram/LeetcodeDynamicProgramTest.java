package com.leetcode.dynamicprogram;

import org.junit.Test;

public class LeetcodeDynamicProgramTest {

    LeetcodeDynamicProgram ld = new LeetcodeDynamicProgram();

    @Test
    public void fibonacci() {
        int n = ld.fibonacci(10);
        n = ld.fibonacci2(10);
        System.out.println(n);
    }
}