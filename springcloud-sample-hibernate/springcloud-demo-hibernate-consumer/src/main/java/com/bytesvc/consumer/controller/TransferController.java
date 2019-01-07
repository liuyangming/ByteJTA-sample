package com.bytesvc.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.consumer.dao.IAccountDao;
import com.bytesvc.consumer.model.Account;
import com.bytesvc.feign.service.IAccountService;

@RestController
public class TransferController {
	@javax.annotation.Resource(name = "accountDao")
	private IAccountDao accountDao;
	@Autowired
	private IAccountService acctService;

	@ResponseBody
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	@Transactional
	public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
		this.acctService.decreaseAmount(sourceAcctId, amount);
		this.increaseAmount(targetAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() + amount);
		this.accountDao.update(account);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
