package com.tr.domain.repositories;

import com.tr.domain.entities.LedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerAccountRepository extends JpaRepository<LedgerAccount, Long> {}
