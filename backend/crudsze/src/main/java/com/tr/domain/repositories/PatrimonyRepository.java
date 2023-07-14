package com.tr.domain.repositories;

import com.tr.domain.entities.Patrimony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrimonyRepository extends JpaRepository<Patrimony, Long> {}
