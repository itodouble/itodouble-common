package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

/**
 * 是否启用状态
 */
public enum EnableStatusEnum implements BaseEnum {
    DISABLE("N", 0, "未启用"),
    ENABLE("Y", 1, "启用");

    private String sn;
    private Integer snData;
    private String snDesc;

    EnableStatusEnum(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }

    public static EnableStatusEnum getBySnData(Integer snData){
        if (null!=snData){
            if (ENABLE.snData==snData){
                return ENABLE;
            }
        }
        return DISABLE;
    }

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
