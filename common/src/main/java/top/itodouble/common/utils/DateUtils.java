package top.itodouble.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    public static final String yyyy = "yyyy";
    public static final String yyyy_mm = "yyyy-MM";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_mm_dd = "yyyy-MM-dd";
    public static final String yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyymmddhhmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddhhmmCN ="yyyy年MM月dd日HH时mm分";
    public static final String hhmmss = "HHmmss";

    public static Date formatYyyyMm(String dateStr) {
        return format(dateStr, yyyy_mm);
    }

    public static Date parseDateYYYYMMDD(String dateStr) {
        return format(dateStr, yyyyMMdd);
    }

    public static String parseDateYYYYMMDD(Date dateStr) {
        return format(dateStr, yyyyMMdd);
    }

    public static String format_yyyy_mm_dd(Date date) {
        return format(date, yyyy_mm_dd);
    }

    public static String format(Date date, String formater) {
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(formater);
            return sdf.format(date);
        }
        return null;
    }

    public static Date format(String dateStr, String formater) {
        if (StringUtils.isNotBlank(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat(formater);
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date format_yyyy_mm_dd_235959(String dateStr){
        if(StringUtils.isNotBlank(dateStr) && dateStr.length()>=19){
            dateStr = dateStr.replace("00:00:00", "23:59:59");
            return format(dateStr, yyyy_mm_dd_hh_mm_ss);
        }
        return null;
    }

    public static Date format_yyyy_mm_dd_235959(Date date){
        if(date!=null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 获取当前时间  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDate(){
        return format(new Date(), yyyy_mm_dd_hh_mm_ss);
    }

    public static String getYear(){
        return format(new Date(), yyyy);
    }

    /**
     * 获取今日开始时间 yyyy-MM-dd 00:00:00
     * @return
     */
    public static String getDateBegin(){
        return format(new Date(), yyyy_mm_dd)+"  00:00:00";
    }

    public static String getDate_yyyymmddhhmmss(){
        return format(new Date(), yyyymmddhhmmss);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 两个时间相差多少个月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int monthDiff(Date date1, Date date2) {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.setTime(date1);
        d2.setTime(date2);
        int year = (d1.get(Calendar.YEAR) - d2.get(Calendar.YEAR)) * 12;
        int month = d1.get(Calendar.MONTH) - d2.get(Calendar.MONTH);
        return year + month;
    }

    /**
     * 相差多少个月 结束日期比开始日期大1算一个月
     * 一个月算30天
     * @param befor
     * @param after
     * @return
     */
    public static String offerMonthDiff(Date befor, Date after) {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.setTime(befor);
        d2.setTime(after);

        int year = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);
        int month = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
        int day = d2.get(Calendar.DATE) - d1.get(Calendar.DATE);
        if(year==0 && month==0 && day==0){
            return "0";
        }
        if(day==-1){ // 刚好满一个月
            return (year * 12 + month)+"";
        }

        if (isMonthBegin(befor) && isMonthEnd(after)){
            return (year * 12 + month+1)+"";
        }

        BigDecimal countMonth = new BigDecimal(year * 12 + month);
        BigDecimal numd = null;
        if (day >= 0) {
            numd = new BigDecimal(day);
            if(year!=0 || month!=0){
                numd = new BigDecimal(day+1);
            }
            countMonth = countMonth.add(numd.divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP));
        } else if (day < 0) {
            countMonth = countMonth.add(new BigDecimal(-1));
            numd = new BigDecimal(30 + day);
            System.out.println(numd);
            countMonth = countMonth.add(numd.divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP));
        }
        return countMonth.toString();
    }

    /**
     * 今日开始时间
     *
     * @return
     */
    public static Date getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    /**
     * 今日结束时间
     *
     * @return
     */
    public static Date getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 两个日期相差多少天
     *
     * @return
     */
    public static int daysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    public static Date stringToDate(String dateText, String format, boolean lenient) {
        if (org.apache.commons.lang.StringUtils.isBlank(dateText)) {
            return null;
        }
        DateFormat df = null;
        try {
            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }
            df.setLenient(false);
            return df.parse(dateText);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date addSecond(Date date, int sec){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, sec);
        return cal.getTime();
    }

    /**
     * 获取这个月 月初的日期
     * @return
     */
    public static Date getMonthBegin(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        return cal.getTime();
    }
    public static Calendar getMonthBeginCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        return cal;
    }

    /**
     * 获取传入日期的这个月最后一天
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Calendar getMonthEndCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal;
    }

    /**
     * 是否为月初
     * @param date
     * @return
     */
    public static Boolean isMonthBegin(Date date){
        Calendar monthBegin = getMonthBeginCalendar(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (monthBegin.get(Calendar.DATE) == cal.get(Calendar.DATE)){
            return true;
        }
        return false;
    }

    /**
     * 是否为月尾
     * @param date
     * @return
     */
    public static Boolean isMonthEnd(Date date){
        Calendar monthEnd = getMonthEndCalendar(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (monthEnd.get(Calendar.DATE) == cal.get(Calendar.DATE)){
            return true;
        }
        return false;
    }

    /**
     * 获取多少天前(后)的日期 毫秒 清空时分秒
     * @param diffDate
     * @return
     */
    public static Long getDateMillis(Integer diffDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.add(Calendar.DAY_OF_MONTH, diffDate);
        return calendar.getTimeInMillis();
    }


    /**
     * 1 Day in Millis
     */
    public static final long DAY = 24L * 60L * 60L * 1000L;

    /**
     * 1 Week in Millis
     */
    public static final long WEEK = 7 * DAY;

    /* An array of custom date formats */
    private static  DateFormat[] CUSTOM_DATE_FORMATS ;
    static {
        String[] possibleDateFormats = {
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",
                /* ISO 8601 */"yyyy-MM-dd'T'",
                /* Simple Date Format */"yyyy-MM-dd"
        };
        CUSTOM_DATE_FORMATS = new SimpleDateFormat[possibleDateFormats.length];

        for (int i = 0; i < possibleDateFormats.length; i++) {
            CUSTOM_DATE_FORMATS[i] = new SimpleDateFormat(possibleDateFormats[i], Locale.getDefault());
            CUSTOM_DATE_FORMATS[i].setTimeZone(TimeZone.getTimeZone("UTC"));
        }
    }

    /* The Default Timezone to be used */
    //private static final TimeZone TIMEZONE = TimeZone.getDefault(); //$NON-NLS-1$

    /**
     * Tries different date formats to parse against the given string
     * representation to retrieve a valid Date object.
     *
     * @param strdate Date as String
     * @return Date The parsed Date
     */
    public static Date parseDate(String strdate) {

        /* Return in case the string date is not set */
        if (strdate == null || strdate.length() == 0) return null;

        Date result = null;
        strdate = strdate.trim();
        if (strdate.length() > 10) {

            /* Open: deal with +4:00 (no zero before hour) */
            if ((strdate.substring(strdate.length() - 5).indexOf("+") == 0 || strdate.substring(strdate.length() - 5).indexOf("-") == 0) && strdate.substring(strdate.length() - 5).indexOf(":") == 2) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String sign = strdate.substring(strdate.length() - 5, strdate.length() - 4);
                strdate = strdate.substring(0, strdate.length() - 5) + sign + "0" + strdate.substring(strdate.length() - 4); //$NON-NLS-1$
            }

            String dateEnd = strdate.substring(strdate.length() - 6);

            /*
             * try to deal with -05:00 or +02:00 at end of date replace with -0500 or
             * +0200
             */
            if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0) && dateEnd.indexOf(":") == 3) { //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                if (!"GMT".equals(strdate.substring(strdate.length() - 9, strdate.length() - 6))) { //$NON-NLS-1$
                    String oldDate = strdate;
                    String newEnd = dateEnd.substring(0, 3) + dateEnd.substring(4);
                    strdate = oldDate.substring(0, oldDate.length() - 6) + newEnd;
                }
            }
        }

        /* Try to parse the date */
        int i = 0;
        while (i < CUSTOM_DATE_FORMATS.length) {
            try {

                /*
                 * This Block needs to be synchronized, because the parse-Method in
                 * SimpleDateFormat is not Thread-Safe.
                 */
                synchronized (CUSTOM_DATE_FORMATS[i]) {
                    return CUSTOM_DATE_FORMATS[i].parse(strdate);
                }
            } catch (ParseException e) {
                i++;
            } catch (NumberFormatException e) {
                i++;
            }
        }
        return result;
    }

    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HH_mm = "HH:mm";

    public static final Map<String, SimpleDateFormat> dateFormatMap = new HashMap<>();



    public static String format_HH_mm(long timeInMillis){
        return getDateFormat(HH_mm).format(timeInMillis);
    }

    public static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat dateFormat = dateFormatMap.get(pattern);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern);
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }

    /**
     * yyyy_MM_dd_HH_mm
     * @param timeInMillis
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm(long timeInMillis) {
        return getDateFormat(yyyy_MM_dd_HH_mm).format(timeInMillis);
    }

    /**
     * yyyy_MM_dd_HH_mm
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm(Date date) {
        return getDateFormat(yyyy_MM_dd_HH_mm).format(date);
    }

    /**
     * yyyy_MM_dd_HH_mm_ss
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_ss(Date date) {
        return format(date, yyyy_mm_dd_hh_mm_ss);
    }

    /**
     * yyyy_MM_dd_HH_mm_ss
     * @param timeInMillis
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_ss(long timeInMillis) {
        return getDateFormat(yyyy_mm_dd_hh_mm_ss).format(timeInMillis);
    }

    /**
     * yyyy_MM_dd_HH_mm_SSS
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_SSS(Date date) {
        return getDateFormat(yyyy_MM_dd_HH_mm_SSS).format(date);
    }

    /**
     * yyyy_MM_dd_HH_mm_SSS
     * @param timeInMillis
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_SSS(long timeInMillis) {
        return getDateFormat(yyyy_MM_dd_HH_mm_SSS).format(timeInMillis);
    }
}
