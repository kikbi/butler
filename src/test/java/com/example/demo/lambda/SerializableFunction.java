package com.example.demo.lambda;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 可序列化lambda
 *
 * @author Tan Ke
 * @date 2021/11/19
 */
public interface SerializableFunction<T, R> extends Function<T, R>, Serializable {

}
