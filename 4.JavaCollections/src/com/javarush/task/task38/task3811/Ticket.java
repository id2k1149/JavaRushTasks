package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Ticket {

    // Должна содержать enum Priority c элементами LOW, MEDIUM, HIGH.
    enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    // свойство priority - по умолчанию Priority.MEDIUM.
    Priority priority() default Priority.MEDIUM;

    // свойство tags - массив строк, пустой по умолчанию.
    String[] tags() default {};

    // свойство createdBy - строку, равную "Amigo" по умолчанию.
    String createdBy() default "Amigo";

}
