package leetcode.hashtable;

import java.util.*;

/**
 * Created by niu_ben on 2016/11/15.
 */
public class LeetcodeHashTable {

    /**
     * 409. Longest Palindrome
     * <p>
     * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
     * This is case sensitive, for example "Aa" is not considered a palindrome here.
     * Note:
     * Assume the length of given string will not exceed 1,010.
     * Example:
     * Input:   "abccccdd"
     * Output:  7
     * Explanation:
     * One longest palindrome that can be built is "dccaccd", whose length is 7.
     */
    public int longestPalindrome(String s) {
        int res = 0;

        if (s == null || "".equals(s) || s.length() == 0) {
            return res;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (char sChar : s.toCharArray()) {
            if (map.containsKey(sChar)) {
                map.put(sChar, map.get(sChar) + 1);
            } else {
                map.put(sChar, 1);
            }
        }
        boolean flag = false;
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer cur = Integer.parseInt(String.valueOf(entry.getValue()));
            if (cur >= 2) {
                if (cur % 2 == 0) {
                    res += cur;
                } else {
                    res += cur - 1;
                    flag = true;
                }
            } else {
                flag = true;
            }
        }

        if (flag) {
            res = res + 1;
        }

        return res;
    }


    /**
     * 350. Intersection of Two Arrays II
     * Given two arrays, write a function to compute their intersection.
     * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        return null;
    }

    /**
     * 349. Intersection of Two Arrays
     * Given two arrays, write a function to compute their intersection.
     * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2]
     * Each element in the result must be unique.
     * The result can be in any order.
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        int[] result = null;
        if (nums1.length == 0 || nums2.length == 0) {
            result = new int[0];
            return result;
        }

        Map resMap = new HashMap();
        for (int i = 0; i < nums2.length; i++) {
            if (!resMap.containsKey(nums2[i])) {
                for (int j = 0; j < nums1.length; j++) {
                    if (nums2[i] == nums1[j]) {
                        resMap.put(nums2[i], 1);
                        break;
                    }
                }
            }
        }

        result = new int[resMap.size()];
        Set x = resMap.keySet();
        Iterator itor = x.iterator();
        int index = 0;
        while (itor.hasNext()) {
            result[index] = Integer.parseInt(String.valueOf(itor.next()));
            index++;
        }

        return result;
    }

    /**
     * 237. Delete Node in a Linked List
     * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
     * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
     * Subscribe to see which companies asked this question
     */
    public static void deleteNode(ListNode node) {
        if (node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 100. Same Tree
     * Given two binary trees, write a function to check if they are equal or not
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {

            if (p.val != q.val) {
                return false;
            }

            if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
                return true;
            }

        } else if (p == null && q == null) {
            return true;
        } else {
            return false;
        }

        return false;
    }

    /**
     * 238. Product of Array Except Self
     * <p/>
     * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     * Solve it without division and in O(n).
     * For example, given [1,2,3,4], return [24,12,8,6].
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        if (nums == null || nums.length == 1) {
            return new int[0];
        }


        for (int i = 0; i < nums.length; i++) {

        }

        return res;
    }

    /**
     * 49. Group Anagrams
     * Given an array of strings, group anagrams together.
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Return: [["ate", "eat","tea"],["nat","tan"],["bat"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String keyStr = String.valueOf(c);

            List<String> curList = new ArrayList<String>();

            if (map.containsKey(keyStr)) {
                curList = map.get(keyStr);
            }

            curList.add(s);

            map.put(keyStr, curList);

        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        return new ArrayList<List<String>>(map.values());

    }

    /**
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     * Note: A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
     */
    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            HashSet<Character> cube = new HashSet<Character>();
            HashMap tableX = new HashMap();
            HashMap tableY = new HashMap();

            for (int j = 0; j < 9; j++) {
                char cx = board[i][j];
                if (cx != '.') {
                    if (cx < '1' || cx > '9')
                        return false;
                    if (tableX.containsKey(cx))
                        return false;
                    tableX.put(cx, 1);
                }


                char cy = board[j][i];
                if (cy != '.') {
                    if (cy < '1' || cy > '9')
                        return false;
                    if (tableY.containsKey(cy))
                        return false;
                    tableY.put(cy, 1);
                }

                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if (board[RowIndex + j / 3][ColIndex + j % 3] != '.' && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
                    return false;
            }
        }


        return true;
    }

    public static void main(String[] args) {
        System.out.println();

        int[] nums = {2, 3, 4, 5};
        System.out.println(Arrays.toString(productExceptSelf(nums)));


//        ListNode node1 = new ListNode(0);
//        ListNode node2 = new ListNode(1);
//        node1.next = node2;
//        node2.next = node1;
//        deleteNode(node2);


//        TreeNode root1 = new TreeNode(0);
//        root1.left = new TreeNode(1);
//        root1.right = new TreeNode(2);
//        root1.left.left = new TreeNode(3);
//        TreeNode root2 = new TreeNode(0);
//        root2.left = new TreeNode(1);
//        root2.right = new TreeNode(2);
//        root2.left.left = new TreeNode(3);
//        System.out.println(isSameTree(root1, root2));


        int[] num1 = {3, 1, 2};
        int[] num2 = {1, 1};
        int[] res = intersect(num1, num2);
        System.out.println(Arrays.toString(res));
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
