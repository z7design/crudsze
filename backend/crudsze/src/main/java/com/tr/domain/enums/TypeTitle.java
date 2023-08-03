package com.tr.domain.enums;

public enum TypeTitle {
  RECEIVABLE("A receber"),
  BILLS_TO_PAY("A pagar");
  
  private String valor;
  
  private TypeTitle(String valor){
    this.valor = valor;
  }
  
  private String getValor(){
    return this.valor;
  }
  
}
