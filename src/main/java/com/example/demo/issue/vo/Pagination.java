package com.example.demo.issue.vo;

import lombok.Data;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@Data
public class Pagination {

    private int pageNo = 1;

    private int pageSize = 20;

    private int totalPage = 1;
}
