package com.example.demo.lambda;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */
@Data
public class SimpleChain<T> implements Serializable {

    private List<SerializableFunction<T, ?>> funcList = new LinkedList<>();

    public SimpleChain next(SerializableFunction<T, ?> sfc) {
        funcList.add(sfc);
        return this;
    }

    public static <T> SimpleChain<T> of(SerializableFunction<T, ?> sfc) {
        SimpleChain<T> simpleChain = new SimpleChain<>();
        return simpleChain.next(sfc);
    }
}
