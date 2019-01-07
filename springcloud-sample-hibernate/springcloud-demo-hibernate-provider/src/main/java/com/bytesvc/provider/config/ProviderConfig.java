package com.bytesvc.provider.config;

import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.UserTransaction;

import org.bytesoft.bytejta.supports.boot.jdbc.DataSourceCciBuilder;
import org.bytesoft.bytejta.supports.boot.jdbc.DataSourceSpiBuilder;
import org.bytesoft.bytejta.supports.springcloud.config.SpringCloudConfiguration;
import org.bytesoft.transaction.TransactionManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Import(SpringCloudConfiguration.class)
@Configuration
public class ProviderConfig implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Bean("jtaTransactionManager")
	public PlatformTransactionManager jtaTransactionManager() {
		org.springframework.transaction.jta.JtaTransactionManager jtaTransactionManager //
				= new org.springframework.transaction.jta.JtaTransactionManager();
		jtaTransactionManager.setTransactionManager(this.applicationContext.getBean(TransactionManager.class));
		jtaTransactionManager.setUserTransaction(this.applicationContext.getBean(UserTransaction.class));
		return jtaTransactionManager;
	}

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

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired @Qualifier("mysql1") DataSource dataSource) {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.transaction.coordinator_class", "jta");
		properties.setProperty("hibernate.transaction.jta.platform", "com.bytesvc.provider.config.SpringJtaPlatform");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJpaProperties(properties);
		entityManager.setDataSource(dataSource);
		return entityManager;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
