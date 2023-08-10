package com.tr.domain.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRespository extends JpaRepository<PhoneEntity, Long> {}
