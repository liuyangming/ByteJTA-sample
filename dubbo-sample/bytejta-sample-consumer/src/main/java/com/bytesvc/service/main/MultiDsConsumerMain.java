package com.bytesvc.service.main;

import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.bytesvc.service.ITransferService;

/**
 * 多数据源场景
 */
@SpringBootApplication(scanBasePackages = { "com.bytesvc.service", "com.bytesvc.config" })
public class MultiDsConsumerMain implements ApplicationContextAware {
	private static ApplicationContext context;

	public static void main(String... args) throws Throwable {
		SpringApplication application = new SpringApplication(GenericConsumerMain.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);

		ITransferService transferSvc = (ITransferService) context.getBean("multiDsTransferService");
		transferSvc.transfer("1001", "2001", 1.00d);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MultiDsConsumerMain.context = applicationContext;
	}

}
