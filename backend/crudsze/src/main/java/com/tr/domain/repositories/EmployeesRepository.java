package com.tr.domain.repositories;

import com.tr.domain.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {}
