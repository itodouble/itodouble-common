package top.itodouble.common.enums;

import org.apache.commons.lang3.StringUtils;
import top.itodouble.common.Base.BaseEnum;

/**
 * 按钮操作类型
 */
public enum MenuOprateType implements BaseEnum {
    READ("read", 1, "读"),
    MODIFY("modify", 2, "添加或修改"),
    DELETE("delete", 3, "删除"),
    SYNC("sync", 4, "同步数据");

    public static MenuOprateType getBySnData(Integer snData) {
        if (null!=snData){
            for (MenuOprateType oprateType: MenuOprateType.values()){
                if (oprateType.snData == snData) {
                    return oprateType;
                }
            }
        }
        return null;
    }

    public static String getDescByData(String data){
        if (StringUtils.isNotBlank(data)){
            for (MenuOprateType oprateType : MenuOprateType.values()) {
                if (data.equals(oprateType.getSnData()+"")){
                    return oprateType.getSnDesc();
                }
            }
        }
        return null;
    }

    public static String getDescBySn(String sn){
        if (StringUtils.isBlank(sn)){
            return null;
        }
        for (MenuOprateType oprateType : MenuOprateType.values()) {
            if (oprateType.getSn().equals(sn)){
                return oprateType.getSnDesc();
            }
        }
        return null;
    }

    MenuOprateType(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }

    private String sn;
    private Integer snData;
    private String snDesc;

    @Override
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public Integer getSnData() {
        return snData;
    }

    public void setSnData(Integer snData) {
        this.snData = snData;
    }

    @Override
    public String getSnDesc() {
        return snDesc;
    }

    public void setSnDesc(String snDesc) {
        this.snDesc = snDesc;
    }
}
