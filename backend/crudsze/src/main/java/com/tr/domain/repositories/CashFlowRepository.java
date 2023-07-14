package com.tr.domain.repositories;

import com.tr.domain.entities.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {}
