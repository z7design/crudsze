package com.tr.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConveter {
    
    public static String convertDateToDateETime(Date date){
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formatter.format(date);
    }
}
