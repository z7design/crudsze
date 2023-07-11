package com.tr.domain.repositories;

import com.tr.domain.entities.TypeAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAccountRepository extends JpaRepository<TypeAccount, Long> {}
