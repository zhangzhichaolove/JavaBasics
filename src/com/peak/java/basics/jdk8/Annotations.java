package com.peak.java.basics.jdk8;

import java.lang.annotation.Repeatable;

public class Annotations {
    public static void main(String[] args) {
        Hint hint = Person2.class.getAnnotation(Hint.class);
        System.out.println(hint);
        Hints hints1 = Person2.class.getAnnotation(Hints.class);
        System.out.println(hints1);
        Hint[] hints2 = Person2.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2);
    }
}

@Hints({@Hint("hint1"), @Hint("hint2")})
class Person1 {

}

@Hint("hint1")
@Hint("hint2")
class Person2 {
}

//@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}