package com.tr.domain.enums;

public enum Nickname {
  HOME("Minha Casa"),
  COMERCIAL("Comérico");
  
  private String nickname;

  Nickname(String apelido) {
    this.nickname = apelido;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
