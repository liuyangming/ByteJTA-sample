package com.bytesvc.sc.eureka.main;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DemoEurekaServerMain {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoEurekaServerMain.class).web(WebApplicationType.SERVLET).run(args);
	}

}
