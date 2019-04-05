package com.workbench.util;

/**
 * Created by niu_ben on 2016/12/5.
 */
public class SearchUtils {

    public int binarySearch(int[] a, int low, int high, int srhVal) {
        int mid;
        if (high <= low) {
            return -1;
        }
        mid = (low + high) >>> 1;
        if (a[mid] > srhVal) {
            return binarySearch(a, low, mid, srhVal);
        } else if (a[mid] < srhVal) {
            return binarySearch(a, mid + 1, high, srhVal);
        } else {
            return mid;
        }

    }
}
