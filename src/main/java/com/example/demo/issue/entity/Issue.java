package com.example.demo.issue.entity;

import com.example.demo.constant.IssuePhaseEnum;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
@ToString(callSuper = true)
public class Issue extends BaseEntity {

    /**
     * 自定义id唯一值由自定义前置+时间戳定义
     */
    private String shortId;

    /**
     * 租户
     */
    private String tenantKey;

    /**
     * 任务标题
     */
    private String subject;

    /**
     * 描述
     */
    private String description;

    /**
     * 所属模块的id，如果模块为项目则为项目id
     */
    private Long moduleId;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 事项类型，可以是自定义类型，默认：需求，任务，缺陷。
     */
    private Long issueTypeId;

    /**
     * 事项状态
     */
    private Long issueStatusId;

    /**
     * 任务阶段
     */
    private IssuePhaseEnum phase;

    /**
     * 指派给
     */
    private Long assignTo;

    /**
     * 创建人
     */
    private Long createdUser;

    /**
     * 紧前任务
     */
    private Long preIssue;

    /**
     * 紧后任务
     */
    private Long postIssue;

    /**
     * 父任务id
     */
    private Long parentId;

    /**
     * 根任务id
     */
    private Long rootId;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 完成百分比
     */
    private BigDecimal doneRatio;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 锁定意味着不可编辑
     */
    private boolean locked;

    /**
     * 删除标识
     */
    private boolean deleted;


}
