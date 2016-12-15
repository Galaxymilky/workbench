package leetcode.string;

import java.util.*;

/**
 * Created by niu_ben on 2016/11/15.
 */
public class LeetcodeString {
    public static String reverseWords(String s) {
        String result = "";
        if ("".equals(s) || s == null) {
            return result;
        }

        String[] array = s.split(" ");
        for (int i = array.length - 1; i > -1; i--) {
            result += array[i] + " ";
        }
        return result.substring(0, result.length() - 1);

    }

    /**
     * 345. Reverse Vowels of a String
     * Write a function that takes a string as input and reverse only the vowels of a string.
     * Given s = "hello", return "holle".
     * Given s = "leetcode", return "leotcede".
     * The vowels does not include the letter "y".
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        String vowels = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {

            while (start < end && !vowels.contains(String.valueOf(chars[start]))) {
                start++;
            }

            while (start < end && !vowels.contains(String.valueOf(chars[end]))) {
                end--;
            }

            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }
        return new String(chars);
    }


    public String reverseVowelsTimeLimit(String s) {

        String vowels = "aeiouAEIOU";

        char[] res = new char[s.length()];
        char[] opt = s.toCharArray();

        int j = opt.length - 1;
        int i = 0;
        while (i < j) {
            char curLeft = opt[i];
            char curRight = opt[j];

            res[i] = curLeft;
            res[j] = curRight;

            boolean flagLeft = vowels.contains(String.valueOf(curLeft));
            boolean flagRight = vowels.contains(String.valueOf(curRight));

            if (flagLeft && flagRight) {
                res[i] = opt[j];
                res[j] = opt[i];
                i++;
                j--;
                continue;
            }
            if (flagLeft && !flagRight) {
                res[j] = opt[j];
                j--;
                continue;
            }
            if (!flagLeft && flagRight) {
                res[i] = opt[i];
                i++;
                continue;
            }
            if (!flagLeft && !flagRight) {
                res[i] = opt[i];
                res[j] = opt[j];
                i++;
                j--;
                continue;
            }

        }
        if (i == j) {
            res[i] = opt[i];
        }

        String str = "";
        for (int k = 0; k < opt.length; k++) {
            str += res[k];
        }
        return str;

    }

    private boolean isWowel(char currChar) {
        String woels = "aeiouAEIOU";
        if (currChar == 'a' || currChar == 'e' || currChar == 'i' || currChar == 'o' || currChar == 'u' || currChar == 'A' || currChar == 'E' || currChar == 'I' || currChar == 'O' || currChar == 'U') {
            return true;
        }
        return false;
    }


    /**
     * 330. Patching Array
     * <p/>
     * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
     */
    public static int minPatches(int[] nums, int n) {

        return -1;
    }

    /**
     * 338. Counting Bits
     * <p/>
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
     * <p/>
     * For num = 5 you should return [0,1,1,2,1,2]
     */
    public static int[] countBits(int num) {
        int[] xy = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            String str = Integer.toBinaryString(i);
            str = str.replaceAll("0", "");
            xy[i] = str.length();
        }
        return xy;
    }


    /**
     * hah one hour ^^
     * 165. Compare Version Numbers
     */
    //0.1 - 0.0.1
    //  1 - 1.1
    // 01 - 1
    //  1 - 1.0.0.0
    public static int compareVersion(String version1, String version2) {

        if (version1.equals(version2)) {
            return 0;
        }

        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        int index = a.length > b.length ? b.length : a.length;

        for (int i = 0; i < index; i++) {
            int x = Integer.parseInt(a[i]);
            int y = Integer.parseInt(b[i]);
            if (x < y) {
                return -1;
            } else if (x > y) {
                return 1;
            } else {
                if (i == index - 1) {
                    int sum_a = 0;
                    int sum_b = 0;
                    for (int j = 0; j < a.length; j++) {
                        sum_a += Integer.parseInt(a[j]);
                    }

                    for (int k = 0; k < b.length; k++) {
                        sum_b += Integer.parseInt(b[k]);
                    }

                    if (sum_a > sum_b) {
                        return 1;
                    } else if (sum_a < sum_b) {
                        return -1;
                    }
                }
            }
        }

        return 0;
    }


    private static int singleNumber(int[] nums) {
        Map map = new HashMap();

        if (nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], 1);
            }
        }

        Set set = map.keySet();
        Iterator ito = set.iterator();
        while (ito.hasNext()) {
            return Integer.parseInt(ito.next() + "");
        }
        return 0;
    }


    /**
     * 258. Add Digits
     * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
     * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
     */
    public static int addDigits(int num) {
        if (num / 10 == 0) {
            return num;
        }

        int tmp = 0;
        String[] array = (num + "").split("");
        for (int i = 0; i < array.length; i++) {
            tmp += Integer.parseInt(array[i]);
        }

        return addDigits(tmp);
    }

    /**
     * 292. Nim Game
     * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who
     * removes the last stone will be the winner. You will take the first turn to remove the stones.
     * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.
     * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
     */
    public static boolean canWinNim(int n) {
        return false;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int x = 1;
        int y = 1;
        if (root.left != null) {
            x = maxDepth(root.left);
            x++;
        }
        if (root.right != null) {
            y = maxDepth(root.right);
            y++;
        }

        if (x > y) {
            return x;
        }

        return y;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 226. Invert Binary Tree
     * Google: 90% of our engineers use the software you wrote (Homebrew), but you can't invert a binary tree on a whiteboard so fuck off.
     */
    public static TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode leftTmpNode = root.left;
        root.left = root.right;
        root.right = leftTmpNode;

        if (root.left != null || root.right != null) {
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    /**
     * 283. Move Zeroes
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     */
    public static void moveZeroes(int[] nums) {
        if (nums != null) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {

                if (nums[i] != 0) {
                    nums[index] = nums[i];
                    index++;
                }

            }

            for (int i = index; i < nums.length; i++) {
                nums[i] = 0;
            }

        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }

    }

    /**
     * 260. Single Number III
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5]
     */
    public static int[] singleNumbers(int[] nums) {
        int[] array = new int[2];
        if (nums == null || nums.length == 0) {
            return array;
        }

        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], 1);
            }
        }

        Iterator ito = map.keySet().iterator();

        array[0] = Integer.parseInt(ito.next() + "");
        array[1] = Integer.parseInt(ito.next() + "");

        return array;
    }

    /**
     *
     * */
    public static void main(String[] args) {
        System.out.println("");
        //String[] s = "111".split("");
        //System.out.println(s.length);

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//
//        root.right.left = new TreeNode(5);
//        root.right.right = new TreeNode(6);
//
//        root.right.right.right = new TreeNode(7);
//        root.right.right.right.left = new TreeNode(8);
//        TreeNode root1 = invertTree(root);
//        System.out.println(maxDepth(root));

        //int[] nums = {0, 0, 1, 0, 0, 1, 0};
        //moveZeroes(nums);

        int[] nums = {0, 3, 1, 3, 2, 1, 2, 7};
        singleNumbers(nums);

    }
}
