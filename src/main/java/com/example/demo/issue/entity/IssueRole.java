package com.example.demo.issue.entity;

import com.example.demo.constant.IssueModuleConst;
import com.example.demo.constant.IssueRoleEnum;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 事项的角色配置，无论那个模块有角色概念需要创建这个角色数据在数据库中
 *
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class IssueRole extends BaseEntity{


    /**
     * 角色标识，每个模块不重复
     */
    private String roleKey;

    /**
     * 所属模块，issue模块为"ISSUE"
     */
    private String module;

    /**
     * 所属模块相关id，如：module：project，moduleId则为项目id
     */
    private Long moduleId;

    /**
     * 租户
     */
    private String tenantKey;

    /**
     * 事项模块默认的几种角色类型
     * @param tenantKey 租户id
     * @return 新租户需要初始化的角色
     */
    public static List<IssueRole> defaultRoles(String tenantKey) {
        return Arrays.stream(IssueRoleEnum.values())
                .map(value -> {
                    IssueRole issueRole = new IssueRole();
                    issueRole.setRoleKey(value.name());
                    issueRole.setModule(IssueModuleConst.MODULE_NAME);
                    issueRole.setModuleId(IssueModuleConst.MODULE_ID);
                    issueRole.setTenantKey(tenantKey);
                    return issueRole;
                })
                .collect(Collectors.toList());
    }

}
