package com.example.demo.configuration;

/**
 * @author Tan Ke
 * @date 2021/11/19
 */
public interface GenId {

    long get();

    long[] getRangeId(int sizeOfIds);
}
