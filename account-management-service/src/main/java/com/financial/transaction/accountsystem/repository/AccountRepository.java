package com.financial.transaction.accountsystem.repository;

import com.financial.transaction.accountsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountNumber(String accountNumber);

    List<Account> findByUserId(String userId);
}
