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

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMenssage() {
    return menssage;
  }

  public void setMenssage(String menssage) {
    this.menssage = menssage;
  }
  
  
}
