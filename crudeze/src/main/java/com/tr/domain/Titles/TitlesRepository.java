package com.tr.domain.Titles;

import com.tr.domain.User.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TitlesRepository extends JpaRepository<TitlesEntity, Long> {
  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM public.titles "
              + "WHERE due_date "
              + "BETWEEN TO_TIMESTAMP(:firstPeriod, 'YYYY-MM-DD hh24:MI:SS') AND "
              + "TO_TIMESTAMP(:finishPeriod, 'YYYY-MM-DD hh24:MI:SS')")
  // obter Fluxo Caixa por data Vencimento
  List<TitlesEntity> getCashFlowByDueDate(
      @Param("firstPeriod") String firstPeriod, @Param("finishPeriod") String finishPeriod);

  List<TitlesEntity> findByUserEntity(UserEntity user);
}
