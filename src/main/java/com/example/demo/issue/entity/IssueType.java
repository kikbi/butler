package com.example.demo.issue.entity;

import lombok.Data;

/**
 * 事项类型
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class IssueType extends BaseEntity{

    private String tenantKey;

    private String name;

    private String i18nId;

    private String module;

    private boolean enabled;

    private boolean deleted;
}
