package com.bytesvc.service.main;

import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.bytesvc.service.ITransferService;

/**
 * 远程调用场景
 */
@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class GenericConsumerMain implements ApplicationContextAware {
	private static ApplicationContext context;

	public static void main(String... args) throws Throwable {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF);
		springApplicationBuilder.sources(GenericConsumerMain.class).web(WebApplicationType.NONE).run(args);

		ITransferService transferSvc = (ITransferService) context.getBean("genericTransferService");
		transferSvc.transfer("1001", "2001", 1.00d);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		GenericConsumerMain.context = applicationContext;
	}

}
