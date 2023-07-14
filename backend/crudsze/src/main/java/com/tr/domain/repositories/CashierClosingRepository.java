package com.tr.domain.repositories;

import com.tr.domain.entities.CashierClosing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierClosingRepository extends JpaRepository<CashierClosing, Long> {}