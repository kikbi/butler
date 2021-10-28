package com.example.demo.issue.entity;

import lombok.Data;

/**
 * 状态变更流转规则
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class StatusChangeRule extends BaseEntity{

    private Long startStatusId;

    private Long endStatusId;

    private String tenantKey;

    private String module;

    private String moduleId;


}
