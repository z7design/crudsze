package com.tr.domain.AccountPayble;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPaybleRespositoy extends JpaRepository<AccountPaybleEntity, Long> {
  
}
