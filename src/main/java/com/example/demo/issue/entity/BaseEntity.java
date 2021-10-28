package com.example.demo.issue.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@Data
public abstract class BaseEntity {
    /**
     * 主键
     */
    @Id
    private Long id;
}
