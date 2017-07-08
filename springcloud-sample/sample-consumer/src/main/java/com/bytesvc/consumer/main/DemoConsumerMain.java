package com.bytesvc.consumer.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath:bytejta-supports-springcloud.xml", "classpath:spring-mybatis.xml" })
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.bytesvc.feign")
@SpringBootApplication(scanBasePackages = "com.bytesvc.consumer")
public class DemoConsumerMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoConsumerMain.class).web(true).run(args);
	}

}
