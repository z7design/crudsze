package com.tr.domain.State;

import java.util.List;
import com.tr.domain.Titles.TitlesEntity;
import com.tr.domain.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
   //List<StateEntity> findByUserEntity(UserEntity user);
}
