package com.bytesvc.provider.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.model.Account;

@RestController
public class AccountController {
	@javax.annotation.Resource(name = "accountDao")
	private IAccountDao accountDao;

	@ResponseBody
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	@Transactional
	public void increaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() + amount);
		this.accountDao.update(account);
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease", method = RequestMethod.POST)
	@Transactional
	public void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		Account account = this.accountDao.findById(acctId);
		account.setAmount(account.getAmount() - amount);
		this.accountDao.update(account);
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
	}

}
