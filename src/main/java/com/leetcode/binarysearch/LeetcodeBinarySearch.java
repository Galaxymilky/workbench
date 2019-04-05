package com.leetcode.binarysearch;

import java.util.Arrays;

/**
 * Created by dynamicniu on 2017/11/5.
 */
public class LeetcodeBinarySearch {
//    public int searchInsert(int[] nums, int target) {
//        int index = nums.length / 2;
//        if (nums[index] > target) {
//            binarySearch();
//        } else {
//            binarySearch(index, );
//        }
//
//        return -1;
//    }

//    private int binarySearch(int pre, int end, int[] nums) {
//        if (nums[]) {
//
//        }
//    }

    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }

        int indexT = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            indexT = t.indexOf(chars[i]);
            if (indexT > -1) {
                t = t.substring(indexT + 1, t.length());
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequenceBinarySearch(String s, String t) {
        return false;
    }

    public boolean isSubsequenceTwoPointers(String s, String t) {
        if (s.length() == 0) {
            return true;
        }

        int indexS = 0;
        int indexT = 0;
        while (indexS < s.length()) {
            if (s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
                if (indexS == s.length()) {
                    return true;
                }
            } else {
                indexT++;
            }
        }
        return false;
    }

    public int findRadius(int[] houses, int[] heaters) {

        if (houses.length == 0 || heaters.length == 0) {
            return 0;
        }

        Arrays.sort(houses);
        Arrays.sort(heaters);

        if (houses[houses.length - 1] < heaters[0] || heaters[heaters.length - 1] < houses[0]) {
            return Math.abs(heaters[0] - houses[0]);
        }

        int begin = 0;
        int end = houses.length - 1;
        int min = 0;

        while (begin <= end) {
            int cur = findMinest(houses[begin], heaters);
            if (cur > min) {
                min = cur;
            }
            begin++;
        }

        return min;
    }

    private int findMinest(int house, int[] heaters) {

        int begin = 0;
        int end = heaters.length - 1;
        int mid = 0;

        while (begin <= end) {
            if (mid != begin + (end - begin) / 2) {
                mid = begin + (end - begin) / 2;
            } else {
                if (Math.abs(heaters[end] - house) > Math.abs(house - heaters[begin])) {
                    return Math.abs(house - heaters[begin]);
                } else {
                    return Math.abs(heaters[end] - house);
                }
            }

            if (house > heaters[mid]) {
                begin = mid;
            } else if (house < heaters[mid]) {
                end = mid;
            } else {
                return 0;
            }
        }

        return heaters[mid] - house;
    }


    public int firstBadVersion(int n) {
        if (n <= 0) {
            return n;
        }

        if (n == 1 && isBadVersion(n)) {
            return 1;
        }

        int begin = 1;
        int end = n;
        int mid = 0;
        while (begin <= end) {
            mid = begin + (end - begin) / 2;

            if (!isBadVersion(mid)) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return begin;
    }

    private boolean isBadVersion(int mid) {
        if (mid == 1) {
            return true;
        } else {
            return false;
        }
    }


    public boolean searchMatrix(int[][] matrix, int target) {

        int row = matrix.length;

        if (matrix == null || row == 0) {
            return false;
        }

        int col = matrix[0].length;

        if (col == 0) {
            return false;
        }

        for (int i = 0; i < row; i++) {

            if (matrix[i][0] > target) {
                int index = i == 0 ? 0 : i - 1;
                for (int j = 0; j < col; j++) {
                    if (matrix[index][j] == target) {
                        return true;
                    }
                }
            } else if (matrix[i][0] == target) {
                return true;
            } else if (row - 1 == i) {
                for (int k = 0; k < col; k++) {
                    if (matrix[i][k] == target) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


}
