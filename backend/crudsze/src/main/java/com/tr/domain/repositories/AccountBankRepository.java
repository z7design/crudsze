package com.tr.domain.repositories;

import com.tr.domain.entities.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBankRepository extends JpaRepository<AccountBank, Long> {}
