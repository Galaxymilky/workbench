package leetcode.greedy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niu_ben on 2017/1/17.
 */
public class LeetcodeGreedyTest {

    LeetcodeGreedy lcGreedy = new LeetcodeGreedy();

    @Test
    public void findContentChildren() throws Exception {
        //res = 2
        int[] gArray = {10, 9, 8, 7};
        int[] sArray = {5, 6, 7, 8};

        //res = 3
//        int[] gArray = {1, 2, 10, 11, 12};
//        int[] sArray = {10, 11, 12};

        //res = 1
//        int[] gArray = {1, 2, 3};
//        int[] sArray = {1, 1};

        int x = lcGreedy.findContentChildren(gArray, sArray);

        System.out.println(x);
    }

}