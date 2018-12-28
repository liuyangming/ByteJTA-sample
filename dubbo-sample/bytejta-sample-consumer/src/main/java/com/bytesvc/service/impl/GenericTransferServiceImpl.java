package com.bytesvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;
import com.bytesvc.service.ITransferService;

@Service(interfaceClass = ITransferService.class, group = "x-bytejta", loadbalance = "bytejta", filter = "bytejta", cluster = "failfast", retries = 0)
@Primary
public class GenericTransferServiceImpl implements ITransferService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Reference(interfaceClass = IAccountService.class, group = "x-bytejta", loadbalance = "bytejta", filter = "bytejta", cluster = "failfast", retries = 0, timeout = 15000)
	private IAccountService remoteAccountService;

	@Transactional(rollbackFor = ServiceException.class)
	public void transfer(String sourceAcctId, String targetAcctId, double amount) throws ServiceException {
		this.remoteAccountService.decreaseAmount(sourceAcctId, amount);
		this.increaseAmount(targetAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) throws ServiceException {
		this.jdbcTemplate.update("update tb_account_two set amount = amount + ? where acct_id = ?", amount, acctId);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
