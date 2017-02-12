package work;

import org.junit.Test;
import work.utils.StrUtils;

/**
 * Created by dynam on 2017/1/9.
 */
public class StrUtilsTest {

    @Test
    public void isNullOrEmptyTest(){
        System.out.println(StrUtils.isNullOrEmpty("1"));;
    }

    @Test
    public void test(){
        String s = "com.jd.".replaceAll(".", "/") + "MyClass.class";
        System.out.println(s);
    }

}
