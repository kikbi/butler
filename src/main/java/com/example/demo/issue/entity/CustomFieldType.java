package com.example.demo.issue.entity;

import com.example.demo.constant.CustomFieldFormatTypeEnum;
import lombok.Data;

/**
 * 自定义字段配置
 *
 * @author Tan Ke
 * @date 2021/10/26
 */
@Data
public class CustomFieldType extends BaseEntity{

    /**
     * 所属租户
     */
    private String tenantKey;

    /**
     * 展示名
     */
    private String displayName;

    /**
     * 类型：ISSUE_CUSTOM_FIELD/PROJECT_CUSTOM_FIELD等{module}+"_CUSTOM_FIELD"
     */
    private String type;

    /**
     * 可能值，一般用于List类型下拉框
     */
    private String possibleValues;

    /**
     * 位置
     */
    private String position;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否多选
     */
    private Boolean multiple;

    /**
     * 自定义字段格式化类型
     */
    private CustomFieldFormatTypeEnum formatType;

    private boolean enabled;

    private boolean deleted;
}
