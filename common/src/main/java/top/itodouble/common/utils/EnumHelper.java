package top.itodouble.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import top.itodouble.common.enums.YesOrNoEnum;
import top.itodouble.common.pojo.EnumEntity;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumHelper {

    /**
     * 将enum转换为list
     *
     * @param clas
     * @return
     */
    public static List<EnumEntity> enumToList(Class clas) {
        if (!"java.lang.Enum".equalsIgnoreCase(clas.getSuperclass().getName())) {
            throw new IllegalArgumentException("The " + clas.getName() + " is not a case of java.lang.Enum");
        }
        List<EnumEntity> dicts = new ArrayList<>();
        try {
            Method mt = clas.getMethod("values");
            Object[] objs = (Object[]) mt.invoke(clas);
            for (Object obj : objs) {
                Class<? extends Object> cls = obj.getClass();
                Method getSnMtd = new PropertyDescriptor("sn", cls).getReadMethod();
                Method getSnDataMtd = cls.getMethod("getSnData");
                Method getDescMtd = cls.getMethod("getSnDesc");
                String sn = getSnMtd.invoke(obj).toString();
                Object data = getSnDataMtd.invoke(obj);
                String desc = getDescMtd.invoke(obj).toString();
                String fval = "";
                Method[] methods = cls.getMethods();
                for (Method method : methods) {
                    if ("getFval".equals(method.getName())) {
                        Method getFvalMtd = cls.getMethod("getFval");
                        fval = getFvalMtd.invoke(obj).toString();
                    }
                }

                if (StringUtils.isNotBlank(sn) && null != data) {
                    EnumEntity dict = new EnumEntity(sn.trim(), data, desc, fval);
                    dicts.add(dict);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CollectionUtils.isNotEmpty(dicts) ? dicts : null;
    }

    /**
     * 将enum转换为list
     *
     * @param clas
     * @return
     */
    public static List<EnumEntity> enumToListWithOutDef(Class clas) {
        if (!"java.lang.Enum".equalsIgnoreCase(clas.getSuperclass().getName())) {
            throw new IllegalArgumentException("The " + clas.getName() + " is not a case of java.lang.Enum");
        }
        List<EnumEntity> dicts = new ArrayList<>();
        try {
            Method mt = clas.getMethod("values");
            Object[] objs = (Object[]) mt.invoke(clas);
            for (Object obj : objs) {
                Class<? extends Object> cls = obj.getClass();
                Method getSnMtd = new PropertyDescriptor("sn", clas).getReadMethod();
                Method getSnDataMtd = new PropertyDescriptor("snData", clas).getReadMethod();
                Method getDescMtd = cls.getMethod("getSnDesc");
                Method getDefaultFlagMtd = cls.getMethod("getDefaultFlag");

                String sn = getSnMtd.invoke(obj).toString();
                Object data = getSnDataMtd.invoke(obj);
                String desc = getDescMtd.invoke(obj).toString();
                Integer defaultFlag = (Integer) getDefaultFlagMtd.invoke(obj);

                if (StringUtils.isNotBlank(sn) && null != data && YesOrNoEnum.YES.getSnData() != defaultFlag) {
                    EnumEntity dict = new EnumEntity(sn.trim(), data, desc);
                    dicts.add(dict);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CollectionUtils.isNotEmpty(dicts) ? dicts : null;
    }


    public static String translateWithDefault(Class clas, Object snDataVal, String def) {
        if (!"java.lang.Enum".equalsIgnoreCase(clas.getSuperclass().getName())) {
            throw new IllegalArgumentException("The " + clas.getName() + " is not a case of java.lang.Enum");
        }
        List<EnumEntity> dicts = new ArrayList<>();
        try {
            Method mt = clas.getMethod("values");
            Object[] objs = (Object[]) mt.invoke(clas);
            for (Object obj : objs) {
                Class<? extends Object> cls = obj.getClass();
                Method getSnMtd = cls.getMethod("getSn");
                Method getSnDataMtd = cls.getMethod("getSnData");
                Method getDescMtd = cls.getMethod("getSnDesc");

                String sn = getSnMtd.invoke(obj).toString();
                Object data = getSnDataMtd.invoke(obj);
                String desc = getDescMtd.invoke(obj).toString();

                if (StringUtils.isNotBlank(sn) && null != data) {
                    EnumEntity dict = new EnumEntity(sn.trim(), data, desc);
                    dicts.add(dict);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isNotEmpty(dicts)) {
            for (EnumEntity entity : dicts) {
                if (entity.getSnData().equals(snDataVal)) {
                    return entity.getSnDesc();
                }
            }
        }
        return def;
    }

    /**
     * 将enum转换为list
     *
     * @param clas
     * @return
     */
    public static List<EnumEntity> enumToArrayList(Class clas) {
        if (!"java.lang.Enum".equalsIgnoreCase(clas.getSuperclass().getName())) {
            throw new IllegalArgumentException("The " + clas.getName() + " is not a case of java.lang.Enum");
        }
        List<EnumEntity> dicts = new ArrayList<>();
        try {
            Method mt = clas.getMethod("values");
            Object[] objs = (Object[]) mt.invoke(clas);


            for (Object obj : objs) {
                Class<? extends Object> cls = obj.getClass();
                Method getSnMtd = cls.getMethod("getSn");
                Method getSnDataMtd = cls.getMethod("getSnData");
                Method getDescMtd = cls.getMethod("getSnDesc");
                String sn = getSnMtd.invoke(obj).toString();
                Object data = getSnDataMtd.invoke(obj);
                String desc = getDescMtd.invoke(obj).toString();

                if (StringUtils.isNotBlank(sn) && null != data) {
                    EnumEntity dict = new EnumEntity(sn.trim(), data, desc);
                    dicts.add(dict);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CollectionUtils.isNotEmpty(dicts) ? dicts : null;
    }

    /**
     * 将enum以键值对的形式存放在map中
     *
     * @param clas
     * @return
     */
    public static Map<String, EnumEntity> enumToMap(Class clas) {
        if (!"java.lang.Enum".equalsIgnoreCase(clas.getSuperclass().getName())) {
            throw new IllegalArgumentException("The " + clas.getName() + " is not a case of java.lang.Enum");
        }
        try {
            Map<String, EnumEntity> map = new HashMap<>();
            Method mt = clas.getMethod("values");
            Object[] objs = (Object[]) mt.invoke(clas);

            for (Object obj : objs) {
                Class<? extends Object> cls = obj.getClass();
                Method getSnMtd = cls.getMethod("getSn");
                Method getDataMtd = cls.getMethod("getSnData");
                Method getDescMtd = cls.getMethod("getSnDesc");
                String sn = getSnMtd.invoke(obj).toString();
                Object data = getDataMtd.invoke(obj);
                String desc = getDescMtd.invoke(obj).toString();
                if (StringUtils.isNotBlank(sn) && null != data) {
                    EnumEntity enumEntity = new EnumEntity(sn, data, desc);
                    map.put(obj.toString(), enumEntity);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将enum以键值对的形式存放在map中 以snData为key
     *
     * @param clas
     * @return
     */
    public static Map<String, EnumEntity> enumToMapSnData(Class clas) {
        if (!"java.lang.Enum".equalsIgnoreCase(clas.getSuperclass().getName())) {
            throw new IllegalArgumentException("The " + clas.getName() + " is not a case of java.lang.Enum");
        }
        try {
            Map<String, EnumEntity> map = new HashMap<>();
            Method mt = clas.getMethod("values");
            Object[] objs = (Object[]) mt.invoke(clas);

            for (Object obj : objs) {
                Class<? extends Object> cls = obj.getClass();
                Method getSnMtd = cls.getMethod("getSn");
                Method getDataMtd = cls.getMethod("getSnData");
                Method getDescMtd = cls.getMethod("getSnDesc");
                String sn = getSnMtd.invoke(obj).toString();
                Object data = getDataMtd.invoke(obj);
                String desc = getDescMtd.invoke(obj).toString();
                if (StringUtils.isNotBlank(sn) && null != data) {
                    EnumEntity enumEntity = new EnumEntity(sn, data, desc);
                    map.put(enumEntity.getSnData() + "", enumEntity);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断code在某个枚举中是否存在
     *
     * @param clas
     * @param sn
     * @return
     */
    public static Boolean checkCode(Class clas, String sn) {
        if (null == sn) {
            return false;
        }
        List<EnumEntity> enumList = EnumHelper.enumToList(clas);
        for (EnumEntity enumEntity : enumList) {
            if (enumEntity.getSn().equals(sn)) {
                return true;
            }
        }
        return false;
    }
}
