package com.tr.domain.Enums;
public enum Nickname {
  HOME("Casa"),
  BEACH_HOUSE("casa de praia"),
  COMERCIAL("comercio");

  private final String value;

  private Nickname(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}