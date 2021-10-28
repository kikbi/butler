package com.example.demo.issue.entity;

import lombok.Data;

/**
 * 自定义字段值
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class CustomFieldValue extends BaseEntity{


    /**
     * 自定义字段配置id
     */
    private Long customFieldId;

    /**
     * 所属事项，可以是issueId也可以是项目id
     */
    private Long belongTo;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 所属租户
     */
    private String tenantKey;

    /**
     * 值
     */
    private String value;
}
