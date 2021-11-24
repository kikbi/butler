package com.example.demo.lambda;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */
public interface SerializablePredicate<T> extends Predicate<T>, Serializable {
}
