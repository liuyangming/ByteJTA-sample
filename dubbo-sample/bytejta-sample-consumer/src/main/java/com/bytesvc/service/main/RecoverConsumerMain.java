package com.bytesvc.service.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RecoverConsumerMain {

	static ClassPathXmlApplicationContext context = null;

	public static void main(String... args) throws Throwable {
		startup();

		try {
			waitForMillis(1000 * 3600);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			shutdown();
		}

	}

	public static void startup() {
		context = new ClassPathXmlApplicationContext("application.xml");
		context.start();
	}

	public static void waitForMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public static void shutdown() {
		if (context != null) {
			context.close();
		}
		System.exit(0);
	}

}
