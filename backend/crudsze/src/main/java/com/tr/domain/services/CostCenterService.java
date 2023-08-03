package com.tr.domain.services;

import com.tr.api.responses.CostCenterResponse;
import com.tr.domain.dto.CostCenterRequestDTO;
import com.tr.domain.entities.CostCenter;
import com.tr.domain.entities.User;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.CostCenterRespository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CostCenterService implements ICrudsServices<CostCenterRequestDTO, CostCenterResponse> {

  @Autowired private CostCenterRespository respository;

  @Autowired private ModelMapper mapper;

  @Override
  public List<CostCenterResponse> findAll() {
    List<CostCenter> list = respository.findAll();
    return list.stream()
        .map(costCenter -> mapper.map(costCenter, CostCenterResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public CostCenterResponse findById(Long costCenterId) {
    Optional<CostCenter> costCenter = respository.findById(costCenterId);
    if (costCenter.isEmpty()) {
      throw new ResourceNotFoundException("Cost Center not found " + costCenterId);
    }
    return mapper.map(costCenter, CostCenterResponse.class);
  }

  @Override
  public CostCenterResponse save(CostCenterRequestDTO dto) {
    CostCenter costCenter = mapper.map(dto, CostCenter.class);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    costCenter.setUser(user);
    costCenter.setCostCenterId(null);
    costCenter = respository.save(costCenter);
    return mapper.map(costCenter, CostCenterResponse.class);
  }

  @Override
  public CostCenterResponse update(Long costCenterId, CostCenterRequestDTO dto) {
    findById(costCenterId);
    CostCenter costCenter = mapper.map(dto, CostCenter.class);

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    costCenter.setUser(user);

    costCenter.setCostCenterId(costCenterId);
    costCenter = respository.save(costCenter);
    return mapper.map(costCenter, CostCenterResponse.class);
  }

  @Override
  public void delete(Long costCenterId) {
    findById(costCenterId);
    respository.deleteById(costCenterId);
  }
}
