package com.atlauncher.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Timestampper {
    private static final SimpleDateFormat format = new SimpleDateFormat();

    private Timestampper(){}

    public static String now(){
        return format.format(new Date());
    }

    public static String was(Date date){
        return format.format(date);
    }
}