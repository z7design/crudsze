package com.tr.domain.repositories;

import com.tr.domain.entities.LedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerAccountRepository extends JpaRepository<LedgerAccount, Long> {}
