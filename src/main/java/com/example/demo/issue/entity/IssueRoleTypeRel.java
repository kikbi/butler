package com.example.demo.issue.entity;

import lombok.Data;

/**
 * 事项角色与类型映射，表明角色可新建类型
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class IssueRoleTypeRel extends BaseEntity{

    private Long roleId;

    private Long issueTypeId;

    private String tenantKey;

}
