package com.example.demo.configuration;

import com.example.demo.issue.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@Slf4j
@Configuration
@EnableJdbcRepositories
public class DataJdbcConfiguration {

    @Bean
    public BeforeSaveCallback<BaseEntity> absEntityBeforeSet() {

        return (entity, aggregateChange) -> {

//            entity.setModified(new Date());
                log.info("实体：{}",entity);
                log.info("集成变更：{}",aggregateChange);
            return entity;

        };

    }
}
