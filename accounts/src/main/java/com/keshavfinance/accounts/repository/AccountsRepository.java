package com.keshavfinance.accounts.repository;

import com.keshavfinance.accounts.entity.Accounts;
import com.keshavfinance.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
}
