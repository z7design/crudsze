package com.tr.domain.exception;

public class ErrorResponse {
  private String dateTime;
  private Integer status;
  private String title;
  private String menssage;

  public ErrorResponse(String dateTime, Integer status, String title, String menssage) {
    this.dateTime = dateTime;
    this.status = status;
    this.title = title;
    this.menssage = menssage;
  }
}
