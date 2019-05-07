package com.leetcode.hashtable;

import org.junit.Test;

import java.util.List;

/**
 * Created by dynamicniu on 2017/6/17.
 */
public class LeetcodeHashTableTest {
    LeetcodeHashTable lcHashTable = new LeetcodeHashTable();

    @Test
    public void testLongestPalindrome() {
        String s = "abccccdd";
        System.out.println(lcHashTable.longestPalindrome(s));
    }

    @Test
    public void testIsValid() {
        char[][] board = {
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'}, {'2', '.', '.', '.', '.', '.', '.', '.', '.'}, {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'}, {'5', '.', '.', '.', '.', '.', '.', '.', '.'}, {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'}, {'8', '.', '.', '.', '.', '.', '.', '.', '.'}, {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        };

        char[][] board_1 = {
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'}, {'.', '4', '.', '3', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'}, {'.', '.', '2', '.', '7', '.', '.', '.', '.'}, {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'}, {'.', '2', '.', '9', '.', '.', '.', '.', '.'}, {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        };

        boolean flag = lcHashTable.isValidSudoku(board_1);
        System.out.println(flag);
    }

    @Test
    public void testGroupAnagrams() {
        String[] strs_0 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs_1 = {"dog", "dog", "ogd"};
        String[] strs_2 = {};
        String[] strs_3 = {"", "", ""};
        List list = lcHashTable.groupAnagrams(strs_2);
        System.out.println(list.toArray().toString());

        // String[] strs_1 = {"dog", "dog", "ogd"};
        // list = lcString.groupAnagrams(strs_1);
        // System.out.println(list.toArray().toString());
    }
}
