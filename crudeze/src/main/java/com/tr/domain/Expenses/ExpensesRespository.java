package com.tr.domain.Expenses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRespository extends JpaRepository<ExpensesEntity, Long> {}
