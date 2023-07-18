package com.tr.domain.services;

import com.tr.domain.entities.CostCenter;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.CostCenterRespository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CostCenterService {
  private static final String MSG_COST_CENTER_NOT_FOUND =
      "There is no state registration with the code %d";
  private static final String MSG_COST_CENTER_IN_USE = "Code state %d cannot be removed as it is in use";

  @Autowired
  private CostCenterRespository costCenterRespository;

  public CostCenter createCostCenter(CostCenter costCenter) {
    return costCenterRespository.save(costCenter);
  }

  @Transactional
  public CostCenter findById(final Long costCenterId) {
    return costCenterRespository
        .findById(costCenterId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_COST_CENTER_NOT_FOUND, costCenterId)));
  }

  @Transactional
  public CostCenter update(final CostCenter costCenter) {
    var entity =
        costCenterRespository
            .findById(costCenter.getCostCenterId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setCode(entity.getCode());
    entity.setDescription(entity.getDescription());
    
    return costCenterRespository.save(costCenter);
  }
  
  public void delete(Long costCenterId) {
    try {
      costCenterRespository.deleteById(costCenterId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_COST_CENTER_NOT_FOUND, costCenterId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_COST_CENTER_IN_USE, costCenterId));
    }
  }

  public List<CostCenter> findAll() {
    return costCenterRespository.findAll();
  }
}
