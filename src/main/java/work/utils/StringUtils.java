package work.utils;

/**
 * Created by niu_ben on 2016/12/28.
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String s) {
        if (s == null || s.length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmptyOrSpace(String s) {
        if (s == null) {
            return true;
        }
        s.trim();
        if (s.length() <= 0) {
            return true;
        }
        return false;
    }

}
