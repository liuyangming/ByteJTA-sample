package com.bytesvc.consumer.dao;

import com.bytesvc.consumer.model.Account;

public interface IAccountDao {

	public Account findById(String identifier);

	public void insert(Account account);

	public void update(Account account);

	public void delete(Account account);

}
