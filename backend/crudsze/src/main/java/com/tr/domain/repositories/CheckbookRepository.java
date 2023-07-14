package com.tr.domain.repositories;

import com.tr.domain.entities.Checkbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckbookRepository extends JpaRepository<Checkbook, Long> {}
