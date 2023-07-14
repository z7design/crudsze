package com.tr.domain.repositories;

import com.tr.domain.entities.AccountPayble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPaybleRepository extends JpaRepository<AccountPayble, Long> {}
