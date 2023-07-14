package com.tr.domain.repositories;

import com.tr.domain.entities.Veicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeicleRepository extends JpaRepository<Veicle, Long> {}
