package com.springTime.bankingWebApplication.repository;

import com.springTime.bankingWebApplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

}
