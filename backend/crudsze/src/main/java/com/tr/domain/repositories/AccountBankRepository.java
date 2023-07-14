package com.tr.domain.repositories;

import com.tr.domain.entities.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBankRepository extends JpaRepository<AccountBank, Long> {}
