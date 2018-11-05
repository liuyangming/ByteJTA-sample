package com.bytesvc.config;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.bytesoft.bytejta.supports.boot.jdbc.DataSourceCciBuilder;
import org.bytesoft.bytejta.supports.boot.jdbc.DataSourceSpiBuilder;
import org.bytesoft.bytejta.supports.dubbo.config.DubboSupportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@Import(DubboSupportConfiguration.class)
@Configuration
@DubboComponentScan(basePackages = { "com.bytesvc.service.impl" })
@EnableDubbo(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class ProviderConfig {

	@Bean("mysqlXA1")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public XADataSource mysqlXA1() {
		return DataSourceSpiBuilder.create().build();
	}

	@Bean("mysql1")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource(@Autowired @Qualifier("mysqlXA1") XADataSource xaDataSourceInstance) {
		return DataSourceCciBuilder.create(xaDataSourceInstance).build();
	}

	@Primary
	@Bean
	public JdbcTemplate jdbcTemplate(@Autowired @Qualifier("mysql1") DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

}
