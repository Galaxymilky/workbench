package work.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Created by niu_ben on 2016/11/15.
 */
public class StrUtils {
    public static void main(String[] args) {
        String str = "aeadbccdebf";
        System.out.println(Test1(str));

        System.out.println(Test2(str));

    }

    private static String Test2(String str) {
        int[] intArray = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            intArray[i] = (int) str.charAt(i);
        }

        int index = firstNoRepeatNum(intArray);

        return (char) intArray[index] + "";
    }

    private static String Test1(String str) {
        return firstNoRepeatChar(str);
    }

    /**
     * Determine whether the string is symmetric
     */
    public static boolean isSymmetricB(String str) {
        int len = str.length();

        if (len == 0 || len == 1) {
            return false;
        }

        char[] chars = str.toCharArray();

        for (int i = 0; i < len / 2; i++) {
            if (chars[i] != chars[len - 1 - i]) {
                return false;
            }
        }
        return true;

    }

    /**
     * Determine whether the string is symmetric
     */
    public static boolean isSymmetricA(String str) {

        int len = str.length();
        if (len == 0 || len == 1) {
            return false;
        }

        if (str.length() % 2 == 0) {

            if (str.substring(0, len / 2).equals(strReverse(str.substring(len / 2, len)))) {
                return true;
            }
        } else {

            if (str.substring(0, len / 2).equals(strReverse(str.substring(len / 2 + 1, len)))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Output the string in reverse order
     */
    public static String strReverse(String s) {
        long startTime = System.currentTimeMillis();

        if (s == null || "".equals(s)) {
            return s;
        }
        char[] cs = s.toCharArray();

        for (int i = 0; i < cs.length / 2; i++) {
            char temp = cs[i];
            cs[i] = cs[cs.length - 1 - i];
            cs[cs.length - 1 - i] = temp;
        }

        System.out.println(System.currentTimeMillis() - startTime);
        return String.valueOf(cs);
    }

    /**
     * DiscardedMethod
     * Output the string in reverse order
     * Time Limit Exceeded
     */
    public static String strReverse_discard(String str) {
        String result = "";
        for (int i = str.length() - 1; i > -1; i--) {
            result += str.charAt(i);
        }
        return result;
    }

    /**
     * String to int array
     */
    public static int[] toIntegerArray(String str) {
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return result;
    }

    /**
     * First no-repeat Num
     *
     * @return int
     */
    public static int firstNoRepeatNum(int[] a) {
        Arrays.sort(a);
        if (a[0] != a[1]) {
            return 0;
        }

        for (int i = 1; i < a.length - 2; i++) {
            if (a[i] + a[i + 1] != 2 * a[i] && a[i + 1] + a[i + 2] != 2 * a[i + 1]) {
                return i + 1;
            }
        }

        return a.length - 1;
    }

    public static String firstNoRepeatChar(String str) {
        LinkedHashMap hashMap = new LinkedHashMap();
        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            Object valObj = hashMap.get(key);
            int val = 0;
            if(valObj != null){
                val = Integer.parseInt(String.valueOf(valObj));
            }
            hashMap.remove(key);
            hashMap.put(key, val+1);
        }

        for(Object obj : hashMap.keySet()){
            String x = String.valueOf(hashMap.get(obj));
            if(Integer.parseInt(x) == 1){
                return String.valueOf(obj);
            }
        }
        return "";
    }

}
