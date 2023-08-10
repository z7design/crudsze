package com.tr.domain.Checkbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckbookRepository extends JpaRepository<CheckbookEntity, Long> {}
