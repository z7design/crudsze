package com.tr.domain.Patrimony;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonyRepository extends JpaRepository<PatrimonyEntity, Long> {}
