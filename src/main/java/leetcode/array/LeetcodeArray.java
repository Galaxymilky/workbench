package leetcode.array;

import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by niu_ben on 2016/11/15.
 * Some solutions still have problem, need to improve, such as [time-limit]
 */
public class LeetcodeArray {
    /**
     * 27.Remove Element
     * Given an array and a value, remove all instances of that value in place and return the new length.
     * <p/>
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * <p/>
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     * <p/>
     * Given input array nums = [3,2,2,3], val = 3
     * <p/>
     * Your function should return length = 2, with the first two elements of nums being 2.
     * {1} 3  return 0; {1} 1
     */
    public int removeElement(int[] nums, int val) {

        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            } else {
                nums[j++] = nums[i];
            }
        }

        return j;
    }

    public int removeElementTwoPoint(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }
        return i;
    }

    /**
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     * <p>
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * <p>
     * Note:
     * You are not suppose to use the library's sort function for this problem.
     */
    public void sortColorsBrust(int[] nums) {
        int nums_0 = 0;
        int nums_1 = 0;
        int nums_2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums_0++;
            } else if (nums[i] == 1) {
                nums_1++;
            } else {
                nums_2++;
            }
        }


        for (int i = 0; i < nums.length; i++) {
            if (i < nums_0) {
                nums[i] = 0;
            } else if (i < nums_1 + nums_0) {
                nums[i] = 1;
            } else if (i < nums.length) {
                nums[i] = 2;
            }
        }
    }

    public void sortColors(int[] nums) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == nums[n - 1]) {
                nums[i+1] = nums[n-1];
                i++;
            } else {
                n--;
            }
        }
    }

    /**
     * 189. Rotate Array
     * <p/>
     * Rotate an array of n elements to the right by k steps.
     * <p/>
     * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
     * <p/>
     * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     * <p/>
     * Could you do it in-place with O(1) extra space?
     * <p/>
     * Related problem: Reverse Words in a String II
     */
    public static void rotate(int[] nums, int k) {
        if (k < 0 || nums.length == 0 || nums == null) {

        } else {
            if (k > nums.length) {
                k = k % nums.length;
            }
            int index = nums.length - k;
            int[] newArray = new int[nums.length];

            for (int i = 0; i < k; i++) {
                newArray[i] = nums[i + index];
            }

            for (int j = 0; j < index; j++) {
                newArray[j + k] = nums[j];
            }

            for (int i = 0; i < nums.length; i++) {
                nums[i] = newArray[i];
            }
        }

    }

    /**
     * Time Limit Exceeded
     */
    public static void rotate_1(int[] nums, int k) {
        if (nums.length != 0 && nums != null) {
            for (int i = 0; i < k + 1; i++) {
                int index = nums[0];
                for (int j = 0; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[nums.length - 1] = index;
            }
        }


    }

    /**
     * 88. Merge Sorted Array
     * <p/>
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * <p/>
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, position = m + n - 1;
        while (j >= 0) {
            nums1[position--] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }

    /**
     * 26. Remove Duplicates from Sorted Array
     * Given a [sorted] array, remove the duplicates in place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {

            } else {
                index++;
                nums[index] = nums[i + 1];
            }
        }
        return index + 1;
    }

    /**
     * 495. Teemo Attacking
     * In LOL world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition.
     * Now, given the Teemo's attacking ascending time series towards Ashe and the poisoning time duration per Teemo's attacking,
     * you need to output the total time that Ashe is in poisoned condition.
     * You may assume that Teemo attacks at the very beginning of a specific time point, and makes Ashe be in poisoned condition immediately.
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {

        if (timeSeries.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            if (timeSeries[i + 1] - timeSeries[i] < duration) {
                count += timeSeries[i + 1] - timeSeries[i];
            } else {
                count += duration;
            }
        }
        return count + duration;
    }

    /**
     * 448. Find All Numbers Disappeared in an Array
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     * Find all the elements of [1, n] inclusive that do not appear in this array.
     * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
     * Input:[4,3,2,7,8,2,3,1]
     * Output:[5,6]
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        Map map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            Set set = map.keySet();
            if (!set.contains(nums[i])) {
                map.put(nums[i], 1);
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (!map.containsKey(j + 1)) {
                list.add(j + 1);
            }
        }

        return list;
    }

    /**
     * 442. Find All Duplicates in an Array
     * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     * Find all the elements that appear twice in this array.
     * Could you do it without extra space and in O(n) runtime?
     * Input:[4,3,2,7,8,2,3,1]
     * Output:[2,3]
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        Map map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                list.add(nums[i]);
            }
        }

        return list;
    }

    /**
     * 414. Third Maximum Number
     * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
     * Input:[3, 2, 1];Output:1
     * Input:[1,2];Output:2
     * Input:[2,2,3,1];Output:1
     */
    public static int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        Map map = new HashMap();
        for (int i = len - 1; i >= 0; i--) {
            if (!map.containsKey(nums[i]) && map.size() == 2) {
                return nums[i];
            } else {
                map.put(nums[i], 1);
            }
        }
        return nums[len - 1];
    }

    /**
     * 380. Insert Delete GetRandom O(1)
     * Design a data structure that supports all following operations in average O(1) time.
     * insert(val): Inserts an item val to the set if not already present.
     * remove(val): Removes an item val from the set if present.
     * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
     */
    public static class RandomizedSet {
        Map map;
        Random random = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            map = new HashMap();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                map.put(val, 1);
            }
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (map.containsKey(val)) {
                map.remove(val);
            } else {
                return false;
            }
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            if (map.size() > 0) {
                Set set = map.keySet();
                Object[] objArray = set.toArray();
                int index = random.nextInt(map.size());
                return (int) objArray[index];
            }
            return -1;
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    /**
     * 381. Insert Delete GetRandom O(1) - Duplicates allowed
     * Design a data structure that supports all following operations in average O(1) time.
     * Note: Duplicate elements are allowed.
     * insert(val): Inserts an item val to the collection.
     * remove(val): Removes an item val from the collection if present.
     * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
     * <p>
     * Init an empty collection.
     * RandomizedCollection collection = new RandomizedCollection();
     * Inserts 1 to the collection. Returns true as the collection did not contain 1.
     * collection.insert(1);
     * Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
     * collection.insert(1);
     * Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
     * collection.insert(2);
     * getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
     * collection.getRandom();
     * Removes 1 from the collection, returns true. Collection now contains [1,2].
     * collection.remove(1);
     * getRandom should return 1 and 2 both equally likely.
     * collection.getRandom();
     */
    public static class RandomizedCollection {
        HashMap<Integer, Set<Integer>> map;
        ArrayList<Integer> list;
        Random random = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            list = new ArrayList<Integer>();
            map = new HashMap<Integer, Set<Integer>>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean contain = map.containsKey(val);
            if (!contain)
                map.put(val, new HashSet<Integer>());
            map.get(val).add(list.size());
            list.add(val);
            return !contain;

        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;
            int loc = map.get(val).iterator().next();
            map.get(val).remove(loc);
            if (loc < list.size() - 1) {
                int lastone = list.get(list.size() - 1);
                list.set(loc, lastone);
                map.get(lastone).remove(map.size() - 1);
                map.get(lastone).add(loc);
            }
            list.remove(list.size() - 1);
            if (map.get(val).isEmpty())
                map.remove(val);
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

    /**
     * 289. Game of Life
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
     * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
     * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population..
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * Write a function to compute the next state (after one update) of the board given its current state.
     */
    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] newBoard = new int[rows + 2][cols + 2];

        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (i == 0 || j == 0 || i == rows + 1 || j == cols + 1) {
                    newBoard[i][j] = 0;
                } else {
                    newBoard[i][j] = board[i - 1][j - 1];
                }

            }
        }

        int[][] resBoard = new int[rows][cols];

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                int x = newBoard[i - 1][j - 1] + newBoard[i - 1][j] + newBoard[i - 1][j + 1] + newBoard[i][j + 1] + newBoard[i + 1][j + 1] + newBoard[i + 1][j] + newBoard[i + 1][j - 1] + newBoard[i][j - 1];

                if (x == 3) {
                    resBoard[i - 1][j - 1] = 1;
                } else if (x == 2) {
                    resBoard[i - 1][j - 1] = board[i - 1][j - 1];
                } else {
                    resBoard[i - 1][j - 1] = 0;
                }

            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = resBoard[i][j];
            }
        }

        board = resBoard;

        for (int i = 0; i < rows; i++) {
            System.out.println(Arrays.toString(board[i]));
        }

    }

    /**
     * 287. Find the Duplicate Number
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
     * Assume that there is only one duplicate number, find the duplicate one.
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     */
    public static int findDuplicate(int[] nums) {
        int len = nums.length;

        if (len == 0 || len == 1) {
            return -1;
        }

        for (int i = 0; i < len - 1; i++) {
            int curVal = nums[i];
            for (int j = i + 1; j < len; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    public static int findDuplicate_better(int[] nums) {
        int slow = 0;
        int fast = 0;
        int finder = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }

        }
        while (true) {
            slow = nums[slow];
            finder = nums[finder];
            if (slow == finder) {
                return slow;
            }
        }

    }

    /**
     * 268. Missing Number
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
     * Given nums = [0, 1, 3] return 2.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
     **/
    public static int missingNumber(int[] nums) {

        int totalVal = 0;
        int totalVal_array = 0;
        for (int i = 0; i < nums.length; i++) {
            totalVal += i + 1;
            totalVal_array += nums[i];
        }

        return totalVal - totalVal_array;
    }

    /**
     * 238. Product of Array Except Self
     * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     * <p>
     * Solve it without division and in O(n).
     * For example, given [1,2,3,4], return [24,12,8,6].given [1,0], return [0,1].
     * Follow up, Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
     */
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int val = 1;
        for (int i = len - 2; i >= 0; i--) {
            val = val * nums[i + 1];
            res[i] *= val;
        }
        return res;
    }

    /**
     * Take time to solve
     */
    public static int[] productExceptSelf_better(int[] nums) {
        multiply(nums, 1, 0, nums.length);
        return nums;
    }

    private static int multiply(int[] nums, int fwdProduct, int index, int N) {
        int revProduct = 1;
        if (index < N) {
            revProduct = multiply(nums, fwdProduct * nums[index], index + 1, N);
            int cur = nums[index];
            nums[index] = fwdProduct * revProduct;
            revProduct *= cur;
        }
        return revProduct;
    }

    /**
     * 229. Majority Element II
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     * The algorithm should run in linear time and in O(1) space.
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();

        int index = (int) Math.floor(nums.length / 3);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= index) {
                list.add(entry.getKey());
            }
        }

        return list;
    }

    public static List<Integer> majorityElement_better(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return list;
        }

        int val1 = nums[0];
        int val2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] == val1) {
                count1++;
            } else if (nums[i] == val2) {
                count2++;
            } else if (val1 == 0) {
                val1 = nums[i];
                count1 = 1;
            } else if (val2 == 0) {
                val2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val1)
                count1++;
            else if (nums[i] == val2)
                count2++;
        }

        if (count1 > len / 3)
            list.add(val1);
        if (count2 > len / 3)
            list.add(val2);
        return list;
    }

    /**
     * 167. Two Sum II - Input array is sorted
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
     * Please note that your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution.
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i + 1);
        }

        for (int i = 0; i < numbers.length; i++) {
            int index = target - numbers[i];
            if (map.containsKey(index)) {
                if (i + 1 > map.get(index)) {
                    res[0] = map.get(index);
                    res[1] = i + 1;
                    return res;
                } else {
                    res[0] = i + 1;
                    res[1] = map.get(index);
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 228. Summary Ranges
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
     */
    public static List<String> summaryRanges(int[] nums) {

        List<String> list = new ArrayList<String>();
        int idx = 0;
        int len = nums.length;

        if (len == 0) {
            return list;
        }

        if (len == 1) {
            list.add(String.valueOf(nums[0]));
            return list;
        }

        for (int i = 0; i < len - 1; i++) {

            idx = i;
            while (i < len - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (idx == i) {
                list.add(String.valueOf(nums[i]));
            } else {
                list.add(nums[idx] + "->" + nums[i]);
            }
        }

        if (nums[len - 1] != nums[len - 2] + 1) {
            list.add(String.valueOf(nums[len - 1]));
        }

        return list;
    }

    /**
     * 217. Contains Duplicate
     * Given an array of integers, find if the array contains any duplicates.
     * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
     */
    public static boolean containsDuplicate(int[] nums) {

        if (nums.length <= 1) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            } else {
                map.put(nums[i], 1);
            }
        }
        return false;
    }

    /**
     * 219. Contains Duplicate II
     * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length <= 1) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i])) <= k) {
                return true;
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * [time-limit]
     * <p>
     * 220. Contains Duplicate III
     * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length <= 1) {
            return false;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int j = ((i + k) > (nums.length - 1) ? (nums.length - 1) : (i + k));
            while (j > i) {
                long val1 = nums[i];
                long val2 = nums[j];
                if (Math.abs(val1 - val2) <= t) {
                    return true;
                } else {
                    j--;
                }
            }
        }
        return false;
    }

    /**
     * 216. Combination Sum III
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     * Input: k = 3, n = 7; Output: [[1,2,4]]
     * Input: k = 3, n = 9; Output: [[1,2,6], [1,3,5], [2,3,4]]
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();


        return list;
    }

    /**
     * 209. Minimum Size Subarray Sum
     * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     * For example, given the array [2,3,1,2,4,3] and s = 7,
     * the subarray [4,3] has the minimal length under the problem constraint.
     * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
     */
    public int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0)
            return 0;

        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

        while (j < a.length) {
            sum += a[j++];

            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= a[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen_TimeLimitExceeded(int s, int[] nums) {
        //i len of subArray
        //j 当前循环
        //k

        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int tmp = 0;
            for (int j = 0; j < len - i + 1; j++) {
                if (j == 0) {
                    for (int k = 0; k < i; k++) {
                        tmp += nums[j + k];
                    }
                } else {
                    tmp = tmp - nums[j - 1] + nums[j + i - 1];
                }

                if (tmp >= s) {
                    return i;
                }
            }
        }

        int tmp1 = 0;
        for (int i = 0; i < len; i++) {
            tmp1 += nums[i];
        }
        if (tmp1 >= s) {
            return len;
        }

        return 0;
    }


    /**
     * 169. Majority Element
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     */
    public int majorityElement169(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int val = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }

            if (map.get(nums[i]) > val) {
                return nums[i];
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > val) {
                return entry.getKey();
            }
        }

        return val;
    }

    public int majorityElement169_clever(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return nums[len / 2];
    }

    /**
     * 162. Find Peak Element
     * A peak element is an element that is greater than its neighbors.
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     * You may imagine that num[-1] = num[n] = -∞.
     * Input:[1, 2, 3, 1], Output:2.
     * <p>
     * logarithmic complexity is needed...
     */
    public int findPeakElement(int[] nums) {
        //int peakIndex = -1;
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return 0;
            } else {
                return 1;
            }
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                return i;
            }
            if (nums[i - 1] <= nums[i] && nums[i] > nums[i + 1]) {
                return i;
            }
            if (nums[i - 1] < nums[i] && nums[i] >= nums[i + 1]) {
                return i;
            }

        }

        return -1;
    }

    /**
     * 120. Triangle
     * <p>
     * [ [2], [3,4], [6,5,7], [4,1,8,3] ]
     * <p>
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     * For example, given the following triangle
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int res = 0;
        int index = 0;
        int maxBottom = 2 ^ (triangle.get(triangle.size() - 1).size() - 1);
        while (index <= maxBottom) {
            for (int i = triangle.size() - 1; i >= 0; i--) {
                res = loopMt(index, i, res);
                index++;
            }
        }
        return res;
    }

    private int loopMt(int index, int height, int sum) {


        return 0;
    }

    /**
     * 79. Word Search
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     */
    public boolean exist(char[][] board, String word) {

        if ("".equals(word)) {
            return false;
        }

        Map<Character, Integer> keyMap = new HashMap<Character, Integer>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (keyMap.containsKey(board[i][j])) {
                    keyMap.put(board[i][j], keyMap.get(board[i][j]) + 1);
                } else {
                    keyMap.put(board[i][j], 1);
                }
            }
        }

        for (int k = 0; k < word.length(); k++) {
            if (keyMap.containsKey(word.charAt(k))) {
                if (keyMap.get(word.charAt(k)) == 0) {
                    return false;
                } else {
                    keyMap.put(word.charAt(k), keyMap.get(word.charAt(k)) - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 74. Search a 2D Matrix
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * [[1, 3, 5, 7],[10, 11, 16, 20],[23, 30, 34, 50]], target=3, return true
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int[] matrixY = matrix[i];
            for (int j = 0; j < matrixY.length; j++) {
                if (matrixY[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 66. Plus One
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public int[] plusOne(int[] digits) {
        return digits;
    }

    /**
     * 59. Spiral Matrix II
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * <p>
     * For example, Given n = 3,
     * You should return the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int round = n % 2 > 0 ? (n / 2 + 1) : n / 2;
        int next = 0;
        for (int i = 0; i < round; i++) {
            int len = n - i - 1;
            for (int j = i; j <= len; j++) {
                next++;
                matrix[i][j] = next; // 0 0-4 || 1 1-3 || 2 2
            }
            next--;

            for (int j = i; j <= len; j++) {
                next++;
                matrix[j][len] = next; // 4 0-4
            }
            next--;

            for (int j = i; j <= len; j++) {
                next++;
                matrix[len][len - j + i] = next; // 4 4-0
            }
            next--;

            for (int j = i; j < len; j++) {
                next++;
                matrix[len - j + i][i] = next; // 0 4-0 len-j
            }
        }
        return matrix;
    }

    /**
     * 54. Spiral Matrix
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * For example, Given the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     * <p>
     * You should return [1,2,3,6,9,8,7,4,5].
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        int row = matrix.length;
        if (matrix == null || row == 0) {
            return list;
        }

        int col = matrix[0].length;
        int min = row > col ? col : row;
        int round = min % 2 > 0 ? (min / 2 + 1) : min / 2;

        for (int i = 0; i < round; i++) {

            row--;
            col--;

            if (col == i) {
                for (int k = i; k <= row; k++) {
                    list.add(matrix[k][i]);
                }
                return list;
            } else if (row == i) {
                for (int k = i; k <= col; k++) {
                    list.add(matrix[i][k]);
                }
                return list;
            } else if (col == row && row == i) {
                list.add(matrix[row][col]);
                return list;
            }

            for (int j = i; j < col; j++) {
                list.add(matrix[i][j]);
            }
            for (int j = i; j < row; j++) {
                list.add(matrix[j][col]);
            }
            for (int j = i; j < col; j++) {
                list.add(matrix[row][col - j + i]);
            }
            for (int j = i; j < row; j++) {
                list.add(matrix[row - j + i][i]);
            }
        }

        return list;
    }

    /**
     * 48. Rotate Image
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * Follow up: Could you do this in-place?
     */
    public void rotate(int[][] matrix) {
        // 3 * 3
        // 00-02 12-23 02-22
        // 10-12 22-22 23-32
        // 20-11 32-21 22-20
        // 780 456 123
        // 147 258 369
        if (matrix.length == 0) {
            return;
        }
        int n = matrix[0].length;
        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] matrixX = new int[n];
            for (int j = n - 1; j >= 0; j--) {
                matrixX[n - j - 1] = matrix[j][i];
            }
            newMatrix[i] = matrixX;
        }
        matrix = newMatrix;
    }

    public void rotate_2(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }

        int n = matrix[0].length;

        // 层数 n=4:2 n=3:1 n=5:2 n=6:3
        int index = n / 2;

        for (int i = 0; i < index; i++) {
            int key = n - i - 1;
            int[] cur = new int[n];
            for (int k = 0; k < n; k++) {
                cur[k] = matrix[i][k];
            }

            int j = i;
            while (j <= key) {
                matrix[i][j] = matrix[key - j + i][i];
                j++;
            }

            j = key;
            while (j >= i) {
                matrix[key - j + i][i] = matrix[key][key - j + i];
                j--;
            }

            j = i;
            while (j <= key) {
                matrix[key][j] = matrix[key - j + i][key];
                j++;
            }

            j = key;
            while (j >= i) {
                matrix[j][key] = cur[j];
                j--;
            }

        }

    }

    /**
     * 39. Combination Sum. Tag[Backtracking]
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Input:{2, 3, 6, 7}, 7; Output:[[7], [2,2,3]]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();


        return list;
    }

    /**
     * 34. Search for a Range
     * Given a sorted array of integers, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     * <p>
     * Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
     */
    public int[] searchRange(int[] nums, int target) {
        int x = getFirstPosBinarySearch(nums, 0, nums.length - 1, target);
        int tmp = (x == -1 ? 0 : x);
        int y = getLastPosBinarySearch(nums, nums.length - 1, tmp, target);
        System.out.println(x + "," + y);
        return new int[]{x, y};
    }

    private int getLastPosBinarySearch(int[] a, int high, int low, int srhVal) {
        int mid;
        if (high < low) {
            return -1;
        }

        if (high == low) {
            if (a[high] == srhVal) {
                return high;
            } else {
                return -1;
            }
        }

        mid = (low + high) >>> 1;
        if (a[mid] > srhVal) {
            return getLastPosBinarySearch(a, mid, low, srhVal);
        } else if (a[mid] < srhVal) {
            return getLastPosBinarySearch(a, high, mid + 1, srhVal);
        } else {
            mid++;
            while (mid <= a.length - 1 && a[mid] == srhVal) {
                mid++;
            }
            return mid - 1;
        }
    }

    private int getFirstPosBinarySearch(int[] a, int low, int high, int srhVal) {
        int mid;
        if (high < low) {
            return -1;
        }

        if (high == low) {
            if (a[high] == srhVal) {
                return high;
            } else {
                return -1;
            }
        }

        mid = (low + high) >>> 1;
        if (a[mid] > srhVal) {
            return getFirstPosBinarySearch(a, low, mid, srhVal);
        } else if (a[mid] < srhVal) {
            return getFirstPosBinarySearch(a, mid + 1, high, srhVal);
        } else {
            mid--;
            while (mid >= 0 && a[mid] == srhVal) {
                mid--;
            }
            return mid + 1;
        }
    }

    /**
     * 21. Merge Two Sorted Lists
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        LeetcodeArray arrayCode = new LeetcodeArray();

        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        System.out.println(randomizedSet.getRandom());

        int[][] array = {{0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 0}, {0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0}};
        gameOfLife(array);

        System.out.printf("Xxx");
        arrayCode.spiralOrder(array);

        int[] array_missingNumber = {0, 1, 2, 3, 4};
        System.out.println(missingNumber(array_missingNumber));

        int[] array_productExceptSelf = {1, 2, 3, 4};
        int[] res_productExceptSelf = productExceptSelf(array_productExceptSelf);
        System.out.println(Arrays.toString(res_productExceptSelf));

        productExceptSelf_better(array_productExceptSelf);


        int[] array_twoSum = {1, 1, 3};
        int target = 2;
        int[] res = twoSum(array_twoSum, target);
        System.out.println(Arrays.toString(res));

        int[] array_summaryRanges = {0, 1, 2, 4, 5, 7, 9};
        List<String> list = summaryRanges(array_summaryRanges);
        for (String i : list) {
            System.out.println(i);
        }

        int[] array_ = {-1, 2147483647};
        System.out.println(containsNearbyAlmostDuplicate(array_, 1, 2147483647));


//        int[] array_findDuplicate_better = {1, 2, 3};
//        System.out.println(findDuplicate_better(array_findDuplicate_better));

//        int[] nums_findDuplicates = {4, 3, 2, 7, 8, 2, 3, 1};
//        List<Integer> list = new ArrayList<Integer>();
//        list = arrayCode.findDuplicates(nums_findDuplicates);

//        int[] nums_thirdMax = {2, 1, 3};
//        int x = thirdMax(nums_thirdMax);
//        System.out.println(x);

//        int[] nums = {1, 1, 1, 2, 2, 3, 5};
//        System.out.println(arrayCode.removeDuplicates(nums));
//        System.out.println(Arrays.toString(nums));


//        int[] nums = {1, 2, 3, 4, 5, 6};
//        int k = 11;
//        rotate(nums, k);
//        rotate_1(nums, k);


//        System.out.print(arrayCode.removeElement(nums, k));


//        int[] nums1 = new int[13];
//        int[] nums2 = {1, 2, 3, 4, 5, 6};
//        for (int i = 0; i < 6; i++) {
//            nums1[i] = i + 1;
//        }
//        arrayCode.merge(nums1, 6, nums2, 6);


//        System.out.println(Arrays.toString(nums1));
    }
}
