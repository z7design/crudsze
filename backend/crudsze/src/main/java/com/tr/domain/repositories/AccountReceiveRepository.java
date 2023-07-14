package com.tr.domain.repositories;

import com.tr.domain.entities.AccountReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReceiveRepository extends JpaRepository<AccountReceive, Long> {}
