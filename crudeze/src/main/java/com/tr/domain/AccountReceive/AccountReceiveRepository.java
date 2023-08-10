package com.tr.domain.AccountReceive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReceiveRepository extends JpaRepository<AccountReceiveEntity, Long> {}
