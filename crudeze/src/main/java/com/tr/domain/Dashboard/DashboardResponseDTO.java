package com.tr.domain.Dashboard;

import java.util.List;
import com.tr.domain.Titles.TitlesResponse;

public class DashboardResponseDTO {
   private Double totalPayable;
   private Double totalReceive;
   private Double balance;

   private List<TitlesResponse> titlesToPay;
   private List<TitlesResponse> titlesToReceive;

  public DashboardResponseDTO(Double totalPayable , Double totalReceive ,
      Double balance, List<TitlesResponse> titlesToPay, List<TitlesResponse> titlesToReceive) {
    this.totalPayable = totalPayable;
    this.totalReceive = totalReceive;
    this.balance = balance;
    this.titlesToPay = titlesToPay;
    this.titlesToReceive = titlesToReceive;
  }

  public Double getTotalPayable() {
    return totalPayable;
  }

  public void setTotalPayable(Double totalPayable) {
    this.totalPayable = totalPayable;
  }

  public Double getTotalReceive() {
    return totalReceive;
  }

  public void setTotalReceive(Double totalReceive) {
    this.totalReceive = totalReceive;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public List<TitlesResponse> getTitlesToPay() {
    return titlesToPay;
  }

  public void setTitlesToPay(List<TitlesResponse> titlesToPay) {
    this.titlesToPay = titlesToPay;
  }

  public List<TitlesResponse> getTitlesToReceive() {
    return titlesToReceive;
  }

  public void setTitlesToReceive(List<TitlesResponse> titlesToReceive) {
    this.titlesToReceive = titlesToReceive;
  }
}
