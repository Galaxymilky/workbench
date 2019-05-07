package com.leetcode.backtracking;

import java.util.*;

/**
 * Created by niu_ben on 2016/11/22.
 */
public class LeetcodeBacktracking {

    /**
     * 401. Binary Watch
     * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
     * Each LED represents a zero or one, with the least significant bit on the right.
     * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
     * <p>
     * Input: n = 1; Output: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     * The order of output does not matter.
     * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
     * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
     */
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] hour = new int[]{8, 4, 2, 1}, second = new int[]{32, 16, 8, 4, 2, 1};
        for (int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(hour, i);
            List<Integer> list2 = generateDigit(second, num - i);
            for (int num1 : list1) {
                if (num1 >= 12)
                    continue;
                for (int num2 : list2) {
                    if (num2 >= 60)
                        continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;

    }

    public List<Integer> generateDigit(int[] data, int count) {
        List<Integer> list = new ArrayList<Integer>();
        generateDigitHelper(data, count, 0, 0, list);
        return list;
    }

    private void generateDigitHelper(int[] data, int count, int pos, int sum, List<Integer> res) {
        if (count == 0) {
            res.add(sum);
            return;
        }

        for (int i = pos; i < data.length; i++) {
            generateDigitHelper(data, count - 1, i + 1, sum + data[i], res);
        }
    }

    public int countNumbersWithUniqueDigitsCombinatorics(int n) {
        if (n == 0) {
            return 1;
        }
        int res = 10;
        int key = 9;
        int validNum = 9;
        while (n-- > 1 && validNum > 0) {
            key = key * validNum;
            res += key;
            validNum--;
        }
        return res;
    }

    /**
     * 357. Count Numbers with Unique Digits
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
     * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
     */
    public int countNumbersWithUniqueDigits(int n) {
        n = n > 10 ? 10 : n;

        int count = 1;
        long max = (long) Math.pow(10, n);

        boolean[] used = new boolean[10];

        for (int i = 1; i < 10; i++) {
            used[i] = true;
            count += search(i, max, used);
            used[i] = false;
        }
        return count;
    }

    public int search(long prev, long max, boolean[] used) {
        int count = 0;

        if (prev < max) {
            count += 1;
        } else {
            return count;
        }

        for (int i = 0; i < 10; i++) {
            if (!used[i]) {
                used[i] = true;
                long cur = 10 * prev + i;
                count += search(cur, max, used);
                used[i] = false;
            }
        }
        return count;
    }

    /**
     * 211. Add and Search Word - Data structure design
     * Design a data structure that supports the following two operations:
     * <p>
     * void addWord(word)
     * bool search(word)
     * <p>
     * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
     * <p>
     * For example:
     * addWord("bad")
     * addWord("dad")
     * addWord("mad")
     * search("pad") -> false
     * search("bad") -> true
     * search(".ad") -> true
     * search("b..") -> true
     */
    public class WordDictionary {

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {

        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {

        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return false;
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */

    /**
     * 46. Permutations
     * Given a collection of distinct numbers, return all possible permutations.
     * For example, [1,2,3] have the following permutations:
     * [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> curList = new ArrayList<>();
        if (nums.length == 1) {
            curList.add(nums[0]);
            list.add(curList);
            return list;
        }



        for (int i = 0; i < nums.length; i++) {

            int[] curNums = new int[nums.length -1];
            int j = 0;
            while (j < nums.length - 1) {
                if (j < i) {
                    curNums[j] = nums[j];
                } else if (j > i) {
                    curNums[j] = nums[j + 1];
                } else {
                    curNums[j] = nums[i + 1];
                }
                j++;
            }

            List<List<Integer>> bList = permute(curNums);
            for (int k = 0; k < bList.size(); k++) {
                List<Integer> aInnerList = new ArrayList<>();
                aInnerList.add(nums[i]);
                aInnerList.addAll(bList.get(k));
                list.add(aInnerList);
            }
        }

        return list;
    }

    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * Input: n = 3; Output: ["((()))","(()())","(())()","()(())","()()()"];
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();

        int[] data = new int[2 * n];
        long max = (long) Math.pow(2, n);
        for (int i = 0; i < n; i++) {

        }
        return list;
    }

    public List<String> generateHelper(long max, int[] data, int sum, int num) {
        List<String> res = new ArrayList<String>();

        for (int i = 0; i < data.length; i++) {

        }

        for (int i = 0; i < num * 2; i++) {

        }

        return res;
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        Map<Integer, String> map = new HashMap();
        map.put(1, "*");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        map.put(0, " ");

        if (digits == null || "".equals(digits)) {
            return list;
        }

        Integer x = Integer.valueOf(digits.substring(0, 1));
        String xs = map.get(x);
        System.out.println(x + " " + xs);

        if (digits.length() > 1) {
            digits = digits.substring(1, digits.length());
        } else {
            digits = "";
        }
        System.out.println(digits);

        List<String> curList = letterCombinations(digits);
        for (int m = 0; m < xs.length(); m++) {
            if (curList == null || curList.size() <= 0) {
                list.add(xs.charAt(m) + "");
            } else {
                for (String str : curList) {
                    list.add(xs.charAt(m) + str);
                }
            }
        }


        return list;
    }

}
