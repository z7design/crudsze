package com.tr.domain.Enums;

public enum TypeTitle {
    
    RECEIVABLE("Receivable"),
    TO_SWITCH_OFF("To switch off");

    private String value;

    private TypeTitle(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
