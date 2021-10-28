package com.example.demo.el;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author Tan Ke
 * @date 2021/10/26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface MyExpression {

    @AliasFor("el")
    String value() default "";

    @AliasFor("value")
    String el() default "";



}
