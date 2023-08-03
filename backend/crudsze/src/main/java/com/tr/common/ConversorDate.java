package com.tr.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDate {
  //Foi criado esse conversor para ultizado em vários lugares do projeto, caso seja necessário
  public static String convertionTheDateToDateAndTime(Date date){
    SimpleDateFormat fomatad = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    return fomatad.format(date);
  }
}
