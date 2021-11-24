package com.example.demo.lambda;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */
public interface SerializableSupplier<T> extends Supplier<T>, Serializable {
}
