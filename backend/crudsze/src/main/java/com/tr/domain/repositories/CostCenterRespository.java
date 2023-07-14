package com.tr.domain.repositories;

import com.tr.domain.entities.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostCenterRespository extends JpaRepository<CostCenter, Long> {}
