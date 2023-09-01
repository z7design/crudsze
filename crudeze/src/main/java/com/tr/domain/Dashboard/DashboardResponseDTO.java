package com.tr.domain.Dashboard;

import com.tr.domain.Titles.TitlesResponse;
import java.math.BigDecimal;
import java.util.List;

public class DashboardResponseDTO {
  private BigDecimal totalPayable = new BigDecimal(0.0);
  private BigDecimal totalReceive = new BigDecimal(0.0);;
  private BigDecimal balance;
  private List<TitlesResponse> titlesToPay;
  private List<TitlesResponse> titlesToReceive;

  public DashboardResponseDTO(
      BigDecimal totalPayable,
      BigDecimal totalReceive,
      BigDecimal balance,
      List<TitlesResponse> titlesToPay,
      List<TitlesResponse> titlesToReceive) {
    this.totalPayable = totalPayable;
    this.totalReceive = totalReceive;
    this.balance = balance;
    this.titlesToPay = titlesToPay;
    this.titlesToReceive = titlesToReceive;
  }

  public BigDecimal getTotalPayable() {
    return totalPayable;
  }

  public void setTotalPayable(BigDecimal totalPayable) {
    this.totalPayable = totalPayable;
  }

  public BigDecimal getTotalReceive() {
    return totalReceive;
  }

  public void setTotalReceive(BigDecimal totalReceive) {
    this.totalReceive = totalReceive;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
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
