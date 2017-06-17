package leetcode.string;

import org.junit.Test;

import java.util.List;

/**
 * Created by niu_ben on 2016/12/13.
 */
public class LeetcodeStringTest {
    LeetcodeString lcString = new LeetcodeString();

    @Test
    public void testRepeatedSubstringPattern() {
        String str = "bbcb";
        str = "acbacba"; //false
        System.out.println(lcString.repeatedSubstringPattern(str));
    }

    @Test
    public void testReverseVowels() {
        String hello = "cAioAio";
        Long startTime = System.currentTimeMillis();
        String s = lcString.reverseVowelsPass(hello);
        Long endTime = System.currentTimeMillis();
        System.out.println(startTime - endTime);
        System.out.println(s);
    }

    @Test
    public void testReverseWords() {
        String str = "The sky is blue";
        str = "   a   b ";
        String res = lcString.reverseWords(str);
        System.out.println(res);
    }


}
