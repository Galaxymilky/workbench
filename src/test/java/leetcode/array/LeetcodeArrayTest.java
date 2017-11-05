package leetcode.array;

import org.junit.Test;
import work.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by niu_ben on 2016/11/23.
 */
public class LeetcodeArrayTest {

    LeetcodeArray lcArray = new LeetcodeArray();

    @Test
    public void testMain() {

        StringBuffer sb1 = new StringBuffer();
        sb1.append("hello");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("hello");

        System.out.println(sb1.equals(sb2));
        System.out.println(sb1 == sb2);

        System.out.println(sb1.toString().equals(sb2.toString()));
        System.out.println(sb1.toString() == sb2.toString());

    }

    @Test
    public void testFindPoisonedDuration() {
        int[] array = {1, 4};
        int duration = 2;
        System.out.println(lcArray.findPoisonedDuration(array, duration));
    }


    @Test
    public void testMissingNumber() {
        int[] array_missingNumber = {0, 1, 2, 3, 4};
        System.out.println(lcArray.missingNumber(array_missingNumber));

    }

    @Test
    public void testRotate() {
        int[][] matrix_0 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix_1 = {};
        lcArray.rotate_2(matrix_0);
    }

    @Test
    public void testGameOfLife() {
        int[][] array = {{0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 0}, {0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0}};
        lcArray.gameOfLife(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testMajorityElement169() {
        int[] array = {1, 2, 2, 3};
        int val = lcArray.majorityElement169(array);
        System.out.println(val);
    }

    @Test
    public void testMajorityElement() {

        System.out.println(3 / 2);

        int[] array = {};
        lcArray.majorityElement(array);
    }

    @Test
    public void testMinSubArrayLen() {
        int s = 697439;
        int[] array = {5334, 6299, 4199, 9663, 8945, 3566, 9509, 3124, 6026, 6250, 7475, 5420, 9201, 9501, 38, 5897, 4411, 6638, 9845, 161, 9563, 8854, 3731, 5564, 5331, 4294, 3275, 1972, 1521, 2377, 3701, 6462, 6778, 187, 9778, 758, 550, 7510, 6225, 8691, 3666, 4622, 9722, 8011, 7247, 575, 5431, 4777, 4032, 8682, 5888, 8047, 3562, 9462, 6501, 7855, 505, 4675, 6973, 493, 1374, 3227, 1244, 7364, 2298, 3244, 8627, 5102, 6375, 8653, 1820, 3857, 7195, 7830, 4461, 7821, 5037, 2918, 4279, 2791, 1500, 9858, 6915, 5156, 970, 1471, 5296, 1688, 578, 7266, 4182, 1430, 4985, 5730, 7941, 3880, 607, 8776, 1348, 2974, 1094, 6733, 5177, 4975, 5421, 8190, 8255, 9112, 8651, 2797, 335, 8677, 3754, 893, 1818, 8479, 5875, 1695, 8295, 7993, 7037, 8546, 7906, 4102, 7279, 1407, 2462, 4425, 2148, 2925, 3903, 5447, 5893, 3534, 3663, 8307, 8679, 8474, 1202, 3474, 2961, 1149, 7451, 4279, 7875, 5692, 6186, 8109, 7763, 7798, 2250, 2969, 7974, 9781, 7741, 4914, 5446, 1861, 8914, 2544, 5683, 8952, 6745, 4870, 1848, 7887, 6448, 7873, 128, 3281, 794, 1965, 7036, 8094, 1211, 9450, 6981, 4244, 2418, 8610, 8681, 2402, 2904, 7712, 3252, 5029, 3004, 5526, 6965, 8866, 2764, 600, 631, 9075, 2631, 3411, 2737, 2328, 652, 494, 6556, 9391, 4517, 8934, 8892, 4561, 9331, 1386, 4636, 9627, 5435, 9272, 110, 413, 9706, 5470, 5008, 1706, 7045, 9648, 7505, 6968, 7509, 3120, 7869, 6776, 6434, 7994, 5441, 288, 492, 1617, 3274, 7019, 5575, 6664, 6056, 7069, 1996, 9581, 3103, 9266, 2554, 7471, 4251, 4320, 4749, 649, 2617, 3018, 4332, 415, 2243, 1924, 69, 5902, 3602, 2925, 6542, 345, 4657, 9034, 8977, 6799, 8397, 1187, 3678, 4921, 6518, 851, 6941, 6920, 259, 4503, 2637, 7438, 3893, 5042, 8552, 6661, 5043, 9555, 9095, 4123, 142, 1446, 8047, 6234, 1199, 8848, 5656, 1910, 3430, 2843, 8043, 9156, 7838, 2332, 9634, 2410, 2958, 3431, 4270, 1420, 4227, 7712, 6648, 1607, 1575, 3741, 1493, 7770, 3018, 5398, 6215, 8601, 6244, 7551, 2587, 2254, 3607, 1147, 5184, 9173, 8680, 8610, 1597, 1763, 7914, 3441, 7006, 1318, 7044, 7267, 8206, 9684, 4814, 9748, 4497, 2239};
        System.out.println(lcArray.minSubArrayLen(s, array));

        s = 120331635;
        FileUtils fileUtils = new FileUtils();
        String diectory = "C:\\";
        String fileName = "data_1.txt";
        String str = fileUtils.readFile(diectory, fileName);

        String[] arrayStr = str.split(",");
        int[] arrayNew = new int[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            arrayNew[i] = Integer.parseInt(arrayStr[i]);
        }
        System.out.println("");

        long startTime = System.currentTimeMillis();
        int res = lcArray.minSubArrayLen(s, arrayNew);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println(time);
        System.out.println(res);

    }

    @Test
    public void testMinSubArrayLenTimeLimit() {
        int s = 120331635;
        FileUtils fileUtils = new FileUtils();
        String diectory = "C:\\";
        String fileName = "data_1.txt";
        String str = fileUtils.readFile(diectory, fileName);

        String[] arrayStr = str.split(",");
        int[] array = new int[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            array[i] = Integer.parseInt(arrayStr[i]);
        }
        System.out.println("");

        long startTime = System.currentTimeMillis();
        int res = lcArray.minSubArrayLen_TimeLimitExceeded(s, array);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println(time);
        System.out.println(res);

    }

    @Test
    public void testSearchRange() {
        int[] a = {};
        int target = 1;
        lcArray.searchRange(a, target);
    }

    @Test
    public void testExist() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        //board = new char[0][0];
        String word = "ABCCED";
        word = "";
        boolean flag = lcArray.exist(board, word);
        System.out.println(flag);
    }


    @Test
    public void testSearchMatrix() {
        int[][] matrix = {};
        int target = 3;
        boolean flag = lcArray.searchMatrix(matrix, target);
        System.out.println(flag);
    }

    @Test
    public void findPeakElement() {
        int[] nums = {1, 2, 3};
        int x = lcArray.findPeakElement(nums);
        System.out.println(x);
    }

    @Test
    public void minimumTotal() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(-1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(-1);
        list3.add(-3);

        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        //triangle.add(list4);
        int res = lcArray.minimumTotal(triangle);
        System.out.println(res);

    }

}
