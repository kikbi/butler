package com.example.demo.configuration;

import java.io.Serializable;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
public interface IdGenerator<ID extends Serializable> {
    ID generate();
}
