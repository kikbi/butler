package com.example.demo.issue.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Pagination pagination;

}
