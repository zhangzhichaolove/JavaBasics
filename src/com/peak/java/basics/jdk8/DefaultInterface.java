package com.peak.java.basics.jdk8;

public class DefaultInterface {
    public static void main(String[] args) {
        Persion p = new Persion() {
        };
        p.eat("苹果");
    }
}

interface Persion {
    default void eat(String str) {
        System.err.println("你吃了：" + str);
    }
}
