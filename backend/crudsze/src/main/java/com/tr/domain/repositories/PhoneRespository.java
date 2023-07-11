package com.tr.domain.repositories;

import com.tr.domain.entities.Phone;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRespository extends JpaRepository<Phone, UUID> {
  
}
