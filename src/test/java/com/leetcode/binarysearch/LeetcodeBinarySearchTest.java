package com.leetcode.binarysearch;

import org.junit.Test;

/**
 * Created by dynamicniu on 2017/11/5.
 */
public class LeetcodeBinarySearchTest {
    LeetcodeBinarySearch lb = new LeetcodeBinarySearch();

//    @Test
//    public void searchInsert() {
//        int[] nums = {1, 3, 5, 6};
//        int target = 0;
//        System.out.println(lb.searchInsert(nums, target));
//    }

    @Test
    public void findRadius() throws Exception {
        int[] houses = {};
        int[] heaters = {};

        int res = lb.findRadius(houses, heaters);
        System.out.println(res);
    }

    @Test
    public void isSubsequence() {
        String s = "leeeeetcode";
        String t = "yyyyylyyyyyyyeyyyyyyyeyyyeyyyytyyyyycyyyyyyoyyyyyyyyyydyyyyey";

        s = "letcode";
        t = "le2tco1de11";
        boolean bool = lb.isSubsequence(s, t);
        System.out.println(bool);
    }

}