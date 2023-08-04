package com.tr.domain.services;

import com.tr.api.responses.TitlesResponse;
import com.tr.domain.dto.DashboardResponseDto;
import com.tr.domain.enums.TypeTitle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

  @Autowired private TitlesService titlesService;

  // obter Fluxo De Caixa
  public DashboardResponseDto getCashFlow(String firstPeriod, String finishPeriod) {

    List<TitlesResponse> titles = titlesService.getByDueDate(firstPeriod, finishPeriod);

    Double totalPayable = 0.0;
    Double totalReceive = 0.0;
    List<TitlesResponse> titlesToPay = new ArrayList<>();
    List<TitlesResponse> titlesToReceive = new ArrayList<>();
    Double balance = 0.0;

    for (TitlesResponse title : titles) {

      if (title.getTypeTitle() == TypeTitle.BILLS_TO_PAY) {
        totalPayable += title.getValueTitle();
        titlesToReceive.add(title);
      } else {
        totalReceive += title.getValueTitle();
        titlesToReceive.add(title);
      }
    }

    balance = totalReceive - totalPayable;

    return new DashboardResponseDto(
        totalPayable, totalReceive, balance, titlesToPay, titlesToReceive);
  }
}
