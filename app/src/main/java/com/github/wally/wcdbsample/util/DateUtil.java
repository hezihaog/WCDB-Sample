package com.github.wally.wcdbsample.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author Feon
 * @date 2017/6/21
 */
public class DateUtil {
    public static final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_NORMAL_MIN = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_LONGDATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_SHORTDATETIME = "yyyy-MM-dd";
    public static final String FORMAT_LONGDATETIME_COMPACT = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_SHORTDATETIME_COMPACT = "yyyyMMdd";
    public static final String FORMAT_FILENAMEL = "yyyyMMdd_HHmmss";

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取当前时间的前/后某个时间  如取当前前11分钟：DateUtil.nowDateChange(Calendar.MINUTE,-11);
     *
     * @param oFlag  单位标识，如Calendar.MINUTE
     * @param amount 差值
     * @return
     */
    public static Date nowDateChange(int oFlag, int amount) {
        return dateChange(new Date(), oFlag, amount);
    }

    /**
     * 取指定时间的前/后某个时间  如取当前前11分钟：DateUtil.nowDateChange(new Date(),Calendar.MINUTE,-11);
     *
     * @param date
     * @param oFlag  单位标识，如Calendar.MINUTE
     * @param amount 差值
     * @return
     */
    public static Date dateChange(Date date, int oFlag, int amount) {
        Date curDateTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(oFlag, amount);
            curDateTime = new Date(cal.getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;
    }


    /**
     * 格式化Date日期对象为指定格式字符串
     *
     * @param argDate
     * @param fFlag   格式：如 DateUtil.FORMAT_NORMAL_DETAIL
     * @return
     */
    public static String getStringFormatByDate(Date argDate, String fFlag) {
        String strDateTime = "";
        try {
            SimpleDateFormat objSDF = new SimpleDateFormat(fFlag.trim());
            strDateTime = objSDF.format(argDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDateTime;
    }

    /**
     * 格式化Date日期对象为指定格式字符串
     *
     * @param argDate
     * @return
     */
    public static String getStringFormatByDate(Date argDate) {
        String strDateTime = "";
        try {
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
            strDateTime = objSDF.format(argDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDateTime;
    }

    /**
     * 格式化Date日期对象为默认格式字符串
     *
     * @param date
     * @return
     */
    public static String getStringDate(Date date) {
        return getStringFormatByDate(date, DateUtil.FORMAT_NORMAL);
    }

    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getNowDateStr() {
        return getStringFormatByDate(new Date(), DateUtil.FORMAT_NORMAL);
    }


    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description:
     */
    public static boolean compareDate(String s, String e) {
        if (formatDate(s) == null || formatDate(e) == null) {
            return false;
        }
        return formatDate(s).getTime() >= formatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date formatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param format 格式 例：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date formatDate(String date, String format) {
        DateFormat fmt = new SimpleDateFormat(format);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //long aa=0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
