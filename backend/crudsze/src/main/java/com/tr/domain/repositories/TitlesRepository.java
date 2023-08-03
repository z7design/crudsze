package com.tr.domain.repositories;

import com.tr.domain.entities.Titles;
import com.tr.domain.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TitlesRepository extends JpaRepository<Titles, Long> {
      @Query(nativeQuery = true, value = "SELECT * FROM public.titles " +
            "WHERE due_date " +
            "BETWEEN TO_TIMESTAMP(:firstPeriod, 'YYYY-MM-DD hh24:MI:SS') AND " +
            "TO_TIMESTAMP(:finishPeriod, 'YYYY-MM-DD hh24:MI:SS')")
      List<Titles> obterFluxoCaixaPorDataVencimento(
        @Param("firstPeriod") String firstPeriod,
        @Param("finishPeriod") String finishPeriod);

    List<Titles> findByUser(User user);
}
