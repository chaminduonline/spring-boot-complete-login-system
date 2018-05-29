package com.jcloud.express.util;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class UtilManager {
    private static final SimpleDateFormat format_sql_full_date = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat year_month = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat day = new SimpleDateFormat("EEEE");
    private static final DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat sourceFormatSql = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date formatHtmlToSql(String date) {
        try {
            return format_sql_full_date.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        return String.valueOf(now.get(Calendar.MONTH));
    }

    public static String getCurrentYear() {
        Calendar now = Calendar.getInstance();
        return String.valueOf(now.get(Calendar.YEAR));
    }

    public static Date getFullDate(YearMonth yyyymm) {
        try {
            return year_month.parse(String.valueOf(yyyymm.atEndOfMonth()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getDay(Date yyyymmdd) {
        try {
            return day.format(yyyymmdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date().toString();
    }

    public static String formatDate(Date date) {
        try {
            return sourceFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date formatDate(String date) {
        try {
            return sourceFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatTimestamp(Date date) {
        try {
            return timestamp.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date().toString();
    }

    public static String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        System.out.println();
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
