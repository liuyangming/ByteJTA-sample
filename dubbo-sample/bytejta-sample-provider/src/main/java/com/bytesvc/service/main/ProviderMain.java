package com.bytesvc.service.main;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class ProviderMain {

	public static void main(String... args) throws Throwable {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF);
		springApplicationBuilder.sources(ProviderMain.class).web(WebApplicationType.NONE).run(args);

		System.out.println("bytejta-sample-provider started!");
	}

}
