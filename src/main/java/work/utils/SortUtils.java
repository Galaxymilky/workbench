package work.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by niu_ben on 2016/11/15.
 */
public class SortUtils {
    public static void main(String[] args) {

        int a[] = {49, 11, 123, 12, 1, 5, 19};
        insertSort(a);
        System.out.println(Arrays.toString(a));

        int[] a2 = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        QuickSorter.quickSort(a2);
        System.out.println(Arrays.toString(a2));

        int a3[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        bubbleSort(a3);
        System.out.println(Arrays.toString(a3));

        int a4[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        HeapSort.heapSort(a4);
        System.out.println(Arrays.toString(a4));

        int a5[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        selectSort(a5);
        System.out.println(Arrays.toString(a5));

        int a6[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        radixSort(a6);
        System.out.println(Arrays.toString(a6));

        int a7[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        MergingSorter.mergingSort(a7);
        System.out.println(Arrays.toString(a7));

        int a8[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        ShellSort(a8);
        System.out.printf(Arrays.toString(a8));
    }

    public static void ShellSort(int[] data) {
        double length = data.length;
        int tmp = 0;
        while (true) {
            length = Math.ceil(length / 2);
            int d = (int) length;
            for (int k = 0; k < length; k++) {
                for (int i = k + d; i < data.length; i++) {
                    int j = i - d;
                    tmp = data[i];
                    for (; j >= 0 && tmp < data[j]; j -= d) {
                        data[j + d] = data[j];
                    }
                    data[j + d] = tmp;
                }
            }

            if (d == 1) {
                break;
            }

        }
    }

    public static void insertSort(int[] array) {
        int insertNode = 0;
        int i, j;
        for (i = 0; i < array.length; i++) {
            insertNode = array[i];
            j = i - 1;

            while (j >= 0 && insertNode < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = insertNode;
        }
    }

    public static class QuickSorter {

        private int[] array;

        public QuickSorter(int[] array) {
            this.array = array;
        }

        public static void quickSort(int[] array) {
            _quick(array, 0, array.length - 1);
        }

        public static void _quick(int[] array, int low, int high) {
            if (low < high) {
                int middle = getMiddle(array, low, high);
                _quick(array, low, middle - 1);
                _quick(array, middle + 1, high);
            }
        }

        private static int getMiddle(int[] array, int low, int high) {
            int tmp = array[low];
            while (low < high) {
                while (low < high && array[high] >= tmp) {
                    high--;
                }
                array[low] = array[high];
                while (low < high && array[low] <= tmp) {
                    low++;
                }
                array[high] = array[low];
            }
            array[low] = tmp;
            return low;
        }

    }

    public static void bubbleSort(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void radixSort(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;

        while (max > 0) {
            max /= 10;
            time++;
        }

        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }

        for (int i = 0; i < time; i++) {
            for (int j = 0; j < array.length; j++) {
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }

        }
    }

    public static class HeapSort {
        public HeapSort() {

        }

        public static void heapSort(int[] a) {
            int arrayLen = a.length;
            for (int i = 0; i < arrayLen - 1; i++) {
                buildMaxHeap(a, arrayLen - 1 - i);
                swap(a, 0, arrayLen - 1 - i);
            }
        }

        private static void buildMaxHeap(int[] data, int lastIndex) {
            for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
                int k = i;
                while (k * 2 + 1 <= lastIndex) {
                    int biggerIndex = 2 * k + 1;
                    if (biggerIndex < lastIndex) {
                        if (data[biggerIndex] < data[biggerIndex + 1]) {
                            biggerIndex++;
                        }
                    }

                    if (data[k] < data[biggerIndex]) {
                        swap(data, k, biggerIndex);
                        k = biggerIndex;
                    } else {
                        break;
                    }

                }


            }
        }

        private static void swap(int[] data, int i, int j) {
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }


    }

    public static class MergingSorter {
        public MergingSorter() {

        }

        public static void mergingSort(int[] a) {
            sort(a, 0, a.length - 1);
        }

        private static void sort(int[] data, int left, int right) {
            if (left < right) {
                int center = (left + right) / 2;
                sort(data, left, center);
                sort(data, center + 1, right);
                merge(data, left, center, right);
            }
        }

        private static void merge(int[] data, int left, int center, int right) {
            int[] tmpArr = new int[data.length];
            int mid = center + 1;
            int third = left;
            int tmp = left;

            while (left <= center && mid <= right) {
                if (data[left] <= data[mid]) {
                    tmpArr[third++] = data[left++];
                } else {
                    tmpArr[third++] = data[mid++];
                }
            }

            while (mid <= right) {
                tmpArr[third++] = data[mid++];
            }

            while (left <= center) {
                tmpArr[third++] = data[left++];
            }

            while (tmp <= right) {
                data[tmp] = tmpArr[tmp++];
            }
        }

    }

    public static void selectSort(int[] a) {

        int position = 0;
        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            position = i;
            int temp = a[i];
            for (; j < a.length; j++) {
                if (a[j] < temp) {
                    temp = a[j];
                    position = j;
                }
            }
            a[position] = a[i];
            a[i] = temp;
        }
    }
}
