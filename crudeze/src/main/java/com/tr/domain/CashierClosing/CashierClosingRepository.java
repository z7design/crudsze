package com.tr.domain.CashierClosing;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierClosingRepository extends JpaRepository<CashierClosingEntity, Long> {}
