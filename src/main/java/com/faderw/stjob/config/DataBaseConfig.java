package com.faderw.stjob.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author FaderW
 * 2019/7/27
 */
@Configuration
@MapperScan(basePackages = "com.faderw.stjob.mapper")
public class DataBaseConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
