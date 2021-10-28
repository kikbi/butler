package com.example.demo.issue.entity;

/**
 * 事项类型与状态关系，每种类型有自己的所属状态
 * @author Tan Ke
 * @date 2021/10/26
 */
public class IssueTypeStatusRel extends BaseEntity{

    private Long issueStatusId;

    private Long issueTypeId;

    private String tenantKey;

    private boolean deleted;
}
