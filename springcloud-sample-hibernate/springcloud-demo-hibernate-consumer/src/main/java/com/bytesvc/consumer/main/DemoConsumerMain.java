package com.bytesvc.consumer.main;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.bytesvc.feign")
@SpringBootApplication(scanBasePackages = "com.bytesvc.consumer")
@EnableJpaRepositories(basePackages = "com.bytesvc.consumer.springdata", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "jtaTransactionManager")
@EntityScan(basePackages = { "com.bytesvc.consumer.model" })
public class DemoConsumerMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoConsumerMain.class).web(WebApplicationType.SERVLET).run(args);
		System.out.println("springcloud-demo-consumer started!");
	}

}
