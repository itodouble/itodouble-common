package top.itodouble.common.Base;


import top.itodouble.common.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public interface BaseEnum {

    String getSn();
    Object getSnData();
    String getSnDesc();

    default short getShortData(){
        try {
            return Short.parseShort(getSnData()+"");
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    default String getSnDataStr(){
        try {
            return getSnData().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    default Integer getDefaultFlag() {
        return 0;
    }

    default String toJsonString(){
        Map<String, Object> map = new HashMap<>();
        map.put("sn", getSn());
        map.put("snData", getSnData());
        map.put("snDesc", getSnDesc());
        return JsonUtils.beanToString(map);
    }
}
