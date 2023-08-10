package com.tr.domain.AccountBank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBankRepository extends JpaRepository<AccountBankEntity, Long> {}
