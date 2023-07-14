package com.tr.domain.repositories;

import com.tr.domain.entities.Veicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeicleRepository extends JpaRepository<Veicle, Long> {}
