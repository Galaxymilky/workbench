package leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niu_ben on 2016/11/23.
 */
public class LeetcodeBacktrackingTest {
    LeetcodeBacktracking lb = new LeetcodeBacktracking();

    @Test
    public void testCountNumbersWithUniqueDigits() {
        int count = lb.countNumbersWithUniqueDigits(3);
        System.out.println(count);
    }

    @Test
    public void testGenerateDigit() {
//        List<String> list = lb.readBinaryWatch(8);
//        for (String s : list) {
//            System.out.println(s);
//        }

//        int[] hour = new int[]{8, 4, 2, 1};
//        List<Integer> list = lb.generateDigit(hour, 3);
//        for(int x : list){
//            System.out.println(x);
//        }

        List arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");

        arrayList.remove(arrayList.size() - 1);
        arrayList.add("2");
        arrayList.add("3");

        for (Object s : arrayList) {
            System.out.println(String.valueOf(s));
        }

    }
}
