package com.demo.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niu_ben on 2016/11/23.
 */
public class BacktrackingTest {

    Backtracking bt = new Backtracking();

    @Test
    public void testSolve1() {
        List<Integer> matrix = new ArrayList<Integer>();
        int j = 0;
        int n = 8;
        bt.solve1(j, matrix, n);

        for (int i = 0; i < matrix.size(); i++) {
            System.out.println(matrix.get(i));
        }
    }

}
