package predicate;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.function.Predicate;

public class PredicateTest {
    @Test
    public void test(){
        Predicate<String> predicate = new StringPredicate();
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("aa"));
    }

    /**
     * 匿名内部类
     */
    @Test
    public void test2(){
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s == null || s.isEmpty();
            }
        };
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("aa"));
    }

    /**
     * lambda表达式
     */
    @Test
    public void test3(){
        Predicate<String> predicate = s -> s == null || s.isEmpty();
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("aa"));
    }

    /**
     * negate
     * 非
     */
    @Test
    public void test4(){
        Predicate<String> predicate = s -> s == null || s.isEmpty();
        System.out.println(predicate.negate().test(""));
        System.out.println(predicate.negate().test("aa"));
    }
    /**
     * or
     * 判断字符串为 aa或者bb
     */
    @Test
    public void test5(){
        Predicate<String> predicate = s-> "aa".equals(s);
        Predicate<String> predicate2 = s-> "bb".equals(s);
        System.out.println(predicate.or(predicate2).test(""));
        System.out.println(predicate.or(predicate2).test("aa"));
    }

    /**
     * and
     * 字符串不为空,且由数字组成,比如 "12", "34"
     */
    @Test
    public void test6(){
        Predicate<String> predicate = s-> s!=null && !s.isEmpty();
        Predicate<String> predicate2 = s-> s!=null && s.chars().allMatch(Character::isDigit);
        System.out.println(predicate.and(predicate2).test("aa"));
        System.out.println(predicate.and(predicate2).test("123"));

    }
    @Test
    public void test7(){
        System.out.println(ZonedDateTime.now());
    }

}
