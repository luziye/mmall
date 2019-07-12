package com.mmall.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateTimeUtil {
    //str->Date
    //Date->str
    private static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";
//    public static Date str2Date(String dateTimeStr,String formatStr){
//        DateTimeFormatter dateTimeFormat=DateTimeFormat.forPattern(formatStr);
//        DateTime dateTime=dateTimeFormat.parseDateTime(dateTimeStr);
//        return dateTime.toDate();
//    }
//    public static  String date2Str(Date date,String formatStr){
//        if (date==null){
//            return StringUtils.EMPTY;
//        }
//        DateTime dateTime=new DateTime(date);
//        return dateTime.toString(formatStr);
//    }
    public static Date str2Date(String dateTimeStr){
        DateTimeFormatter dateTimeFormat=DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime=dateTimeFormat.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    public static String date2Str(Date date){
        if (date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

//    public static void main(String[] args) {
//        System.out.println(DateTimeUtil.date2Str(new Date(),STANDARD_FORMAT));
//        System.out.println(DateTimeUtil.str2Date("2019-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
//    }
}
