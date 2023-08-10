package com.tr.domain.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRespository extends JpaRepository<AddressEntity, Long> {
  
}
