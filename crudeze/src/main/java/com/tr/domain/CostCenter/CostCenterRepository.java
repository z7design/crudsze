package com.tr.domain.CostCenter;

import com.tr.domain.User.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostCenterRepository extends JpaRepository<CostCenterEntity, Long> {
    List<CostCenterEntity> findByUserEntity(UserEntity userEntity);
}
