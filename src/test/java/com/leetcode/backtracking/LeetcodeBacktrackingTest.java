package com.leetcode.backtracking;

import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by niu_ben on 2016/11/23.
 */
public class LeetcodeBacktrackingTest {
    LeetcodeBacktracking lb = new LeetcodeBacktracking();

    @Test
    public void testMain() {
        int x = lb.countNumbersWithUniqueDigitsCombinatorics(11);
        System.out.println(x);
    }

    @Test
    public void simpleRecursion() {
        System.out.println("吓得我抱起了");
        String s = "抱着我的小鲤鱼de我";
        int n = 0;
        String str = baoqilai(s, n);
        System.out.println(str);
    }

    private String baoqilai(String s, int n) {
        if (n < 3) {
            s = baoqilai(s, ++n);
        }
        s = "抱着" + s + "的我";
        return s;
    }

    @Test
    public void testCountNumbersWithUniqueDigits() {
        int count = lb.countNumbersWithUniqueDigits(5);
        System.out.println(count);
    }

    @Test
    public void testGenerateParenthesis() {
        List<String> list = lb.generateParenthesis(4);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void testGenerateDigit() {
//        List<String> list = lb.readBinaryWatch(8);
//        for (String s : list) {
//            System.out.println(s);
//        }

//        int[] hour = new int[]{8, 4, 2, 1};
//        List<Integer> list = lb.generateDigit(hour, 3);
//        for(int x : list){
//            System.out.println(x);
//        }

        List arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");

        arrayList.remove(arrayList.size() - 1);
        arrayList.add("2");
        arrayList.add("3");

        for (Object s : arrayList) {
            System.out.println(String.valueOf(s));
        }
    }

    @Test
    public void testPermute() {
        int[] nums = new int[]{1,2,3,4,5};
        List<List<Integer>> list = lb.permute(nums);
        for (List<Integer> aList : list) {
            for (Integer val : aList) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testLetterCombinations() {
        String digits = "23";
        List<String> list = lb.letterCombinations(digits);
        for (String s : list) {
            System.out.print(s + ",");
        }
    }
}
