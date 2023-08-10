package com.tr.domain.Installment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {
  
}
