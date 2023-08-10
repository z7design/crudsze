package com.tr.domain.Dashboard;

import com.tr.domain.Enums.TypeTitle;
import com.tr.domain.Titles.TitlesResponse;
import com.tr.domain.Titles.TitlesService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    
    @Autowired
    private TitlesService titleService;

  // obter Fluxo De Caixa
  public DashboardResponseDTO getCashFlow(String firstPeriod, String finishPeriod) {

    //lista de titles por data de vencimento
    List<TitlesResponse> titles = titleService.getCashFlowByDueDate(firstPeriod, finishPeriod);

    Double totalPayable = 0.0;
    Double totalReceive = 0.0;
    List<TitlesResponse> titlesToPay = new ArrayList<>();
    List<TitlesResponse> titlesToReceive = new ArrayList<>();
    Double balance = 0.0;

    for (TitlesResponse title : titles) {

      if (title.getTypeTitle() == TypeTitle.TO_SWITCH_OFF) {
        totalPayable += title.getValueTitle();
        titlesToReceive.add(title);
      } else {
        totalReceive += title.getValueTitle();
        titlesToReceive.add(title);
      }
    }

    balance = totalReceive - totalPayable;

    return new DashboardResponseDTO(
        totalPayable, totalReceive, balance, titlesToPay, titlesToReceive);
  }
}
