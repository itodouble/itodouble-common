package top.itodouble.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ObjectUtils extends org.apache.commons.lang.ObjectUtils {
    public static Short toShort(Object obj) {
        try {
            return Short.parseShort(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer toInteger(Object obj) {
        try {
            if(obj instanceof Integer){
                return (Integer) obj;
            }
            return Integer.valueOf(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer toInteger(Object obj, Integer def) {
        if(StringUtils.isNotNull(obj)){
            try {
                if(obj instanceof Integer){
                    return (Integer) obj;
                }
                return Integer.valueOf(obj.toString());
            } catch (Exception e) {
                return null;
            }
        }
        return def;
    }

    public static List<Integer> toListInteger(Object obj){
        List<Integer> intList = new ArrayList<>();
        List<Object> objList = (List)obj;
        if(null!=objList){
            objList.forEach(each->{
                Integer i = toInteger(each);
                if(null!=i){
                    intList.add(i);
                }
            });
            return intList;
        }
        return null;
    }

    public static String toString (Object obj){
        if (null == obj) {
            return null;
        }
        try {
            String str = obj.toString();
            return org.apache.commons.lang.StringUtils.trimToNull(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toStringWithDef (Object obj, String def){
        String s = toString(obj);
        if (null != s){
            return s+"";
        }
        return def;
    }

    public static String toStringWithDef (Short s, String def){
        if (null != s){
            return s+"";
        }
        return def;
    }

    public static BigDecimal toBigDecimal(Object obj) {
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date toDate(Object obj){
        String str = ObjectUtils.toString(obj);
        if (null == str){
            return null;
        }
        return DateUtils.parseDate(str);
    }

    public static Date to59Date(Object obj){
        Date date = DateUtils.parseDate(ObjectUtils.toString(obj));
        if(null!=date){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MINUTE, 59);
            return calendar.getTime();
        }
        return null;
    }

    public static int sumWithZero(Object... objs) {
        int res = 0;
        for (Object obj: objs) {
            res = res+toInteger(obj, 0);
        }
        return res;
    }

    /**
     * 将obj1中不为空的值 复制到obj2中
     * @param obj1
     * @param obj2
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object copyPropertiesWithOutNull(Object obj1,Object obj2) throws InvocationTargetException, IllegalAccessException {
        Method[] method1 = obj1.getClass().getMethods();
        Method[] method2 = obj2.getClass().getMethods();
        String methodName1;
        String methodFix1;
        String methodName2;
        String methodFix2;

        for (int i = 0; i < method1.length; i++) {
            methodName1 = method1[i].getName();
            methodFix1 = methodName1.substring(3, methodName1.length());
            if (methodName1.startsWith("get")) {
                Object val1 = method1[i].invoke(obj1);
                // 如果为空时 直接跳过
                if (null == val1){
                    continue;
                }
                if (val1 instanceof String) {
                    if (org.apache.commons.lang.StringUtils.isBlank(val1.toString())){
                        continue;
                    }
                }

                for (int j = 0; j < method2.length; j++) {
                    methodName2 = method2[j].getName();
                    methodFix2 = methodName2.substring(3, methodName2.length());
                    if (methodName2.startsWith("set")) {
                        if (methodFix2.equals(methodFix1)) {
                            method2[j].invoke(obj2, val1);
                            continue;
                        }
                    }
                }
            }
        }
        return obj2;
    }
}
