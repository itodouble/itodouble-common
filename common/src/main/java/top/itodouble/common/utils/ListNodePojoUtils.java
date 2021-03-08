package top.itodouble.common.utils;



import top.itodouble.common.pojo.ListNodePojo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListNodePojoUtils {
    /**
     *
     * @param list
     * @param columnName get方法字段
     * @param restoreKeyName 重新存储时 ListNodePojo.key
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static List<ListNodePojo> cover(List list, String columnName, Object restoreKeyName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        List<ListNodePojo> resultList = new ArrayList<>();
        for (Object obj : list) {
            ListNodePojo nodeVo = new ListNodePojo();
            Method getSnMtd = new PropertyDescriptor(columnName, obj.getClass()).getReadMethod();
            Object keyVal = getSnMtd.invoke(obj);
            nodeVo.setKey(restoreKeyName);
            nodeVo.setVal(keyVal);
            nodeVo.setOriginal(obj);
            resultList.add(nodeVo);
        }
        System.out.println(JsonUtils.beanToString(resultList));
        return resultList;
    }

}
