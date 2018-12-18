package com.bytesvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.ServiceException;
import com.bytesvc.service.IAccountService;
import com.bytesvc.service.ITransferService;

@Service("multiDsTransferService")
public class MultiDsTransferServiceImpl implements ITransferService {
	@Autowired
	@Qualifier("accountService")
	private IAccountService nativeAccountService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(rollbackFor = ServiceException.class)
	public void transfer(String sourceAcctId, String targetAcctId, double amount) throws ServiceException {
		this.increaseAmount(targetAcctId, amount);
		this.nativeAccountService.decreaseAmount(sourceAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) throws ServiceException {
		this.jdbcTemplate.update("update tb_account_two set amount = amount + ? where acct_id = ?", amount, acctId);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
