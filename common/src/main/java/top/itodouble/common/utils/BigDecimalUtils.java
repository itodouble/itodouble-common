package top.itodouble.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static Short coverToShortDefaultZero(BigDecimal num){
        if (null != num){
            return num.shortValue();
        }
        return 0;
    }

    public static Short coverToShort(BigDecimal num, Integer defaultVal) {
        if (null != num){
            return num.shortValue();
        }
        return defaultVal.shortValue();
    }

    public static Short coverToShortDefaultZero(Long num){
        if (null != num){
            return num.shortValue();
        }
        return 0;
    }
    public static Short coverToShort(Long num, Integer defaultVal) {
        if (null!=num){
            return num.shortValue();
        }
        return defaultVal.shortValue();
    }

    public static Short coverIntegerToShor(Integer num) {
        if (null != num){
            return num.shortValue();
        }
        return null;
    }


    public static Integer coverToInteger(BigDecimal num) {
        if (null!=num){
            return num.intValue();
        }
        return null;
    }

    public static Integer coverToIntegerDefaultZero(BigDecimal num) {
        if (null!=num){
            return num.intValue();
        }
        return 0;
    }


}
