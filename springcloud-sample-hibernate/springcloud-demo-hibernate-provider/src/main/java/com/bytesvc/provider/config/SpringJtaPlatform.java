package com.bytesvc.provider.config;

import org.bytesoft.bytejta.TransactionBeanFactoryImpl;
import org.bytesoft.transaction.TransactionBeanFactory;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

public class SpringJtaPlatform extends AbstractJtaPlatform {
	private static final long serialVersionUID = 1L;

	protected javax.transaction.TransactionManager locateTransactionManager() {
		TransactionBeanFactory beanFactory = TransactionBeanFactoryImpl.getInstance();
		return beanFactory == null ? null : beanFactory.getTransactionManager();
	}

	protected javax.transaction.UserTransaction locateUserTransaction() {
		return null;
	}
}
