package com.example.demo.issue.entity;

import lombok.Data;

/**
 * 任务自定义状态，默认有"未开始"、"进行中"、"已完成"三种状态，对应三个阶段
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class IssueStatus extends BaseEntity{

    private String tenantKey;

    private String name;

    private String i18nId;

    private String module;

    private boolean enabled;

    private boolean deleted;

}
