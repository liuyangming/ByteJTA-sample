package com.bytesvc.provider.main;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.bytesvc.provider")
@EnableJpaRepositories(basePackages = "com.bytesvc.provider.springdata", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "jtaTransactionManager")
@EntityScan(basePackages = { "com.bytesvc.provider.model" })
public class DemoProviderMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoProviderMain.class).web(WebApplicationType.SERVLET).run(args);
		System.out.println("springcloud-demo-provider started!");
	}

}
