package com.tr.domain.repositories;

import com.tr.domain.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRespository extends JpaRepository<Expenses, Long> {}
