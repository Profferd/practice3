package com.intern.practice3.task2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Instant;

@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
    String name() default "";

    String format() default "dd.MM.yyyy HH:mm";
}
