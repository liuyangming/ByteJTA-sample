package com.bytesvc.consumer.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytesvc.consumer.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, String> {
}
