package com.tr.domain.repositories;

import com.tr.domain.entities.Patrimony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonyRepository extends JpaRepository<Patrimony, Long> {}
