package com.peak.java.basics.Extends;

/**
 * 在调用子类构造方法之前会先调用父类没有参数的构造方法,其目的是?
 * 帮助子类做初始化工作。
 */
public class Test extends SuperTest {

    public Test() {
        System.err.println("子类构造执行。");
    }
}


class SuperTest {
    public SuperTest() {
        System.err.println("父类构造执行。");
    }
}

