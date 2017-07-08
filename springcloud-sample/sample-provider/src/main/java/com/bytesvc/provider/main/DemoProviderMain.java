package com.bytesvc.provider.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath:bytejta-supports-springcloud.xml" })
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.bytesvc.provider")
public class DemoProviderMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoProviderMain.class).web(true).run(args);
	}

}
