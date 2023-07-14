package com.tr.domain.repositories;

import com.tr.domain.entities.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long> {}
