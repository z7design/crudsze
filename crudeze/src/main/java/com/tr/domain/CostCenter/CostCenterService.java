package com.tr.domain.CostCenter;

import com.tr.domain.ICrudsServices;
import com.tr.domain.User.UserEntity;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CostCenterService implements ICrudsServices<CostCenterRequestDTO, CostCenterResponse>{

  @Autowired
  private CostCenterRepository respository;

  @Autowired
  private ModelMapper mapper;

  @Override
  public List<CostCenterResponse> findAll() {
    UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<CostCenterEntity> list = respository.findByUserEntity(userEntity);

    return list.stream()
        .map(costCenter -> mapper.map(costCenter, CostCenterResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public CostCenterResponse findById(Long costCenterId) {
    Optional<CostCenterEntity> costCenterEntity = respository.findById(costCenterId);
     UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (costCenterEntity == null || costCenterEntity.get().getUserEntity().getUserId() != user.getUserId()) {
      throw new ResourceNotFoundException("Cost Center not found " + costCenterId);
    }
    return mapper.map(costCenterEntity, CostCenterResponse.class);
  }

  @Override
  public CostCenterResponse save(CostCenterRequestDTO dto) {

    CostCenterEntity costCenter = mapper.map(dto, CostCenterEntity.class);

    UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    costCenter.setUserEntity(userEntity);
    costCenter.setCostCenterId(null);
    costCenter = respository.save(costCenter);

    return mapper.map(costCenter, CostCenterResponse.class);
  }

  @Override
  public CostCenterResponse update(Long costCenterId, CostCenterRequestDTO dto) {
    findById(costCenterId);
    CostCenterEntity costCenter = mapper.map(dto, CostCenterEntity.class);

    UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    costCenter.setUserEntity(userEntity);

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

