package com.leetcode.greedy;

import java.util.*;

/**
 * Created by niu_ben on 2017/1/17.
 */
public class LeetcodeGreedy {

    /**
     * 455. Assign Cookies
     * Assume you are an awesome parent and want to give your children some cookies.
     * But, you should give each child at most one cookie.
     * Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with;
     * and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content.
     * Your goal is to maximize the number of your content children and output the maximum number.
     * <p>
     * Input: [1,2,3], [1,1]    Output: 1
     * Input: [1,2], [1,2,3]    Output: 2
     */
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;

        int index = 0;
        for (int i = 0; i < s.length; i++) {
            int curCookies = s[i];

            for (int j = index; j < g.length; j++) {
                if (curCookies >= g[j]) {
                    result++;
                    index++;
                    break;
                }
            }

        }

        return result;
    }

    /**
     * 406. Queue Reconstruction by Height
     * <p>
     * Suppose you have a random list of people standing in a queue.
     * Each person is described by a pair of integers (h, k),
     * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
     * Write an algorithm to reconstruct the queue.
     * <p>
     * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
        int rowLen = people.length;

        for (int i = 0; i < rowLen - 1; i++) {
            for (int j = 0; j < rowLen - 1 - i; j++) {
                if (people[j][0] > people[j + 1][0]) {
                    int[] tmp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < rowLen - 1; i++) {
            for (int j = 0; j < rowLen - 1 - i; j++) {
                if(people[j][1] > people[j + 1][1]){
                    int[] tmp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = tmp;
                }
            }
        }

        return people;
    }


}
