package predicate;

import org.junit.Test;

import java.util.function.Predicate;

public class PredicateTest {

    @Test
    public void test() {
        Predicate<String> predicate = new StringPredicate();
        System.out.println(predicate.test(""));//true
        System.out.println(predicate.test("aa"));//false
    }

    /**
     * 匿名内部类
     */
    @Test
    public void test2() {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s==null||s.isEmpty();
            }
        };
        System.out.println(predicate.test(""));//true
        System.out.println(predicate.test("aaa"));//false
    }

    /**
     * lambda表达式
     */
    @Test
    public void test3() {
        Predicate<String> predicate = s -> s == null ||s.isEmpty();
        System.out.println(predicate.test(""));//true
        System.out.println(predicate.test("aaa"));//false
    }

    /**
     * negate
     * 非
     */
    @Test
    public void test4() {
        Predicate<String> predicate = s -> s == null || s.isEmpty();
        System.out.println(predicate.negate().test(""));//false
        System.out.println(predicate.negate().test("aaa"));//true
    }

    /**
     * or
     * 判断字符串为 aa或者bb
     */
    @Test
    public void test5() {
        Predicate<String> predicate = s -> "aa".equals(s);
        Predicate<String> predicate2 = s -> "bb".equals(s);
        System.out.println(predicate.or(predicate2).test(""));//false
        System.out.println(predicate.or(predicate2).test("aa"));//true
    }

    /**
     * and
     * 字符串不为空,且由数字组成,比如 "12", "34"
     */
    @Test
    public void test6() {
        Predicate<String> predicate = s -> s!=null || !s.isEmpty();
        Predicate<String> predicate2 = s -> s!=null && s.chars().allMatch(Character::isDigit);
        System.out.println(predicate.and(predicate2).test("aa"));//false
        System.out.println(predicate.and(predicate2).test("123"));//true
     }
}
