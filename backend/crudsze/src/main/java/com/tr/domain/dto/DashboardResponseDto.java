package com.tr.domain.dto;

import com.tr.api.responses.TitlesResponse;
import java.util.List;

public class DashboardResponseDto {
   private Double totalPayable;
   private Double totalReceive;
   private Double balance;
   
   private List<TitlesResponse> titlesToPay;
   private List<TitlesResponse> titlesToReceive;

  public DashboardResponseDto(Double totalAccountsPayable, Double totalAccountsReceive,
      Double balance, List<TitlesResponse> titlesToPay, List<TitlesResponse> titlesToReceive) {
    this.totalPayable = totalPayable;
    this.totalReceive = totalReceive;
    this.balance = balance;
    this.titlesToPay = titlesToPay;
    this.titlesToReceive = titlesToReceive;
  }

  public DashboardResponseDto() {
  }

  public Double getTotalAccountsPayable() {
    return totalPayable;
  }

  public void setTotalAccountsPayable(Double totalAccountsPayable) {
    this.totalPayable = totalAccountsPayable;
  }

  public Double getTotalAccountsReceive() {
    return totalReceive;
  }

  public void setTotalAccountsReceive(Double totalAccountsReceive) {
    this.totalReceive = totalAccountsReceive;
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
