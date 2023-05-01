package com.yael.springboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //언제까지 살아있을 것인지
@Target(ElementType.TYPE) //어노테이션이 적용될 유형
@Component
public @interface MyComponent {
    /*

     */
}
