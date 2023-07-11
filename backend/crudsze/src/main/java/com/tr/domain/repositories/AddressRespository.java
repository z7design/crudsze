package com.tr.domain.repositories;

import com.tr.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRespository extends JpaRepository<Address, Long> {
  
}
