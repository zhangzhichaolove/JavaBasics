package com.peak.java.basics.jdk8;

import java.util.*;

public class Lambda {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("苹果", "梨子", "香蕉", "桃子");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Collections.sort(names, (String o1, String o2) -> {
            return o1.compareTo(o2);
        });
        Collections.sort(names, (String o1, String o2) -> o1.compareTo(o2));
        Collections.sort(names, String::compareTo);
        names.sort((a, b) -> b.compareTo(a));
        System.err.println(names);

        //Java 8允许您通过::关键字传递方法或构造函数的引用。
        //Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Converter<String, byte[]> converter = String::getBytes;
        byte[] converted = converter.convert("123");
        System.out.println(converted);

        //Person::new 来获取Person类构造函数的引用，Java编译器会自动根据PersonFactory.create方法的参数类型来选择合适的构造函数。
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("张", "三");
        System.err.println(person);

        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
        // 测试 Filter(过滤)
        stringList
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        System.err.println("---------------------------");
        System.err.println(stringList);
        // 测试 Sort (排序)
        //排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的
        stringList
                .stream()
                .sorted((a, b) -> a.compareTo(b))
                .forEach(System.out::println);
        System.err.println("---------------------------");
        System.err.println(stringList);
        // 测试 Map 操作
        stringList
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))//.count()
                .forEach(System.out::println);
        //测试 Reduce (规约)操作
        Optional<String> reduced =
                stringList
                        .parallelStream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        System.err.println("---------------------------");
        reduced.ifPresent(System.out::println);
    }
}

//“函数式接口”是指仅仅只包含一个抽象方法,但是可以有多个非抽象方法(也就是上面提到的默认方法)的接口。 像这样的接口，可以被隐式转换为lambda表达式。
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Person {
    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}