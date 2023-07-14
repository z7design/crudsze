package com.tr.domain.repositories;

import com.tr.domain.entities.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostCenterRespository extends JpaRepository<CostCenter, Long> {}
