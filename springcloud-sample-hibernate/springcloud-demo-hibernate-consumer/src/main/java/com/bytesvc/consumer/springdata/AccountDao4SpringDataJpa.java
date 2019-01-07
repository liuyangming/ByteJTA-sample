package com.bytesvc.consumer.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bytesvc.consumer.dao.IAccountDao;
import com.bytesvc.consumer.model.Account;

@Component("accountDao")
public class AccountDao4SpringDataJpa implements IAccountDao {
	@Autowired
	private AccountRepository repository;

	public Account findById(String identifier) {
		return this.repository.findById(identifier).get();
	}

	public void insert(Account account) {
		this.repository.saveAndFlush(account);
	}

	public void update(Account account) {
		this.repository.saveAndFlush(account);
	}

	public void delete(Account account) {
		this.repository.delete(account);
	}

}
