package top.itodouble.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    public JsonUtils() {
    }

    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        } else {
            Class<?> clazz = value.getClass();
            if (clazz != Integer.TYPE && clazz != Integer.class) {
                if (clazz == String.class) {
                    return (String)value;
                } else {
                    return clazz != Long.TYPE && clazz != Long.class ? JSON.toJSONString(value, new SerializerFeature[]{SerializerFeature.UseISO8601DateFormat}) : "" + value;
                }
            } else {
                return "" + value;
            }
        }
    }

    /** @deprecated */
    @Deprecated
    public static <T> String beanToStringOld(T value) {
        if (value == null) {
            return null;
        } else {
            Class<?> clazz = value.getClass();
            if (clazz != Integer.TYPE && clazz != Integer.class) {
                if (clazz == String.class) {
                    return (String)value;
                } else {
                    return clazz != Long.TYPE && clazz != Long.class ? JSON.toJSONString(value) : "" + value;
                }
            } else {
                return "" + value;
            }
        }
    }

    /**
     * 将json字符串转换为对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str != null && str.length() > 0 && clazz != null) {
            if (clazz != Integer.TYPE && clazz != Integer.class) {
                if (clazz == String.class) {
                    return (T) str;
                } else if (clazz != Long.TYPE && clazz != Long.class) {
                    if (clazz != Boolean.TYPE && clazz != Boolean.class) {
                        return clazz == List.class ? JSON.toJavaObject(JSON.parseArray(str), clazz) : JSON.toJavaObject(JSON.parseObject(str), clazz);
                    } else {
                        return (T) Boolean.valueOf(str);
                    }
                } else {
                    return (T) Long.valueOf(str);
                }
            } else {
                return (T) Integer.valueOf(str);
            }
        } else {
            return null;
        }
    }

    /**
     * 将json字符串转换为对象(Object)
     * @param str
     * @return
     */
    public static Object stringToBean(String str) {
        return str != null && str.length() > 0 ? JSON.parseObject(str) : null;
    }

    /**
     * 将json字符串转换为某个对象的列表
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToListBean(String str, Class<T> clazz) {
        if (str != null && str.length() > 0) {
            JSONArray array = JSON.parseArray(str);
            return convertJSONArrayToTypeList(array, clazz);
        } else {
            return null;
        }
    }

    /**
     * 将JSONArray转换为某个对象的列表
     * @param jsonArray
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJSONArrayToTypeList(JSONArray jsonArray, Class<T> clz) {
        if (CollectionUtils.isEmpty(jsonArray)) {
            return new ArrayList();
        } else {
            List<T> result = new ArrayList(jsonArray.size());
            jsonArray.forEach((element) -> {
                if (!(element instanceof String) && !(element instanceof Number) && !(element instanceof Boolean)) {
                    T t = JSONObject.toJavaObject((JSONObject)element, clz);
                    result.add(t);
                } else {
                    result.add((T) element);
                }

            });
            return result;
        }
    }

    /**
     * 将JSONObject转换为某个对象
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertJSONObjectToType(JSONObject obj, Class<T> clazz) {
        return obj == null ? null : stringToBean(beanToString(obj), clazz);
    }

    /**
     * 将Object对象转换为map
     * @param map 为空时会new
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> beanToMap(Map<String, Object> map, Object obj) throws IllegalAccessException {
        if (null == map) map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            map.put(fieldName, field.get(obj));
        }
        return map;
    }

    /**
     * 将Object对象转换为map
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> beanToMap(Object obj) throws IllegalAccessException {
        return beanToMap(null, obj);
    }
}
