package leetcode.string;

import org.junit.Test;

/**
 * Created by niu_ben on 2016/12/13.
 */
public class LeetcodeStringTest {
    LeetcodeString lcString = new LeetcodeString();

    @Test
    public void testReverseVowels() {
        String hello = "ceae";
        Long startTime = System.currentTimeMillis();
        String s = lcString.reverseVowels(hello);
        Long endTime = System.currentTimeMillis();
        System.out.println(startTime - endTime);
        System.out.println(s);
    }

}
