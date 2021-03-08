package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

public enum YesOrNoEnum implements BaseEnum {
    YES("Y", 1, "是",  "1", "有", true),
    NO("N", 0,"否", "0", "无", false);

    private String sn;
    private Integer snData;
    private String snDesc;

    private String snDataStr;
    private String snDataStr2;
    private Boolean bool;

    public static YesOrNoEnum getBySnData(Integer snData) {
        if (null!= snData){
            if (YES.snData==snData){
                return YES;
            }
        }
        return NO;
    }

    public static String getSnDescBySnData(Integer snData, String def) {
        if (null != snData){
            if (YES.snData == snData){
                return YES.snDesc;
            } else if (NO.snData == snData){
                return NO.snDesc;
            }
        }
        return def;
    }


    YesOrNoEnum(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }


    YesOrNoEnum(String sn, Integer snData, String snDesc, String snDataStr) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;

        this.snDataStr = snDataStr;
    }

    YesOrNoEnum(String sn, Integer snData, String snDesc, String snDataStr, String snDataStr2) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
        this.snDataStr = snDataStr;
        this.snDataStr2 = snDataStr2;
    }

    YesOrNoEnum(String sn, Integer snData, String snDesc, String snDataStr, String snDataStr2, Boolean bool) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
        this.snDataStr = snDataStr;
        this.snDataStr2 = snDataStr2;
        this.bool = bool;
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

    public String getSnDataStr() {
        return snDataStr;
    }

    public void setSnDataStr(String snDataStr) {
        this.snDataStr = snDataStr;
    }

    public String getSnDataStr2() {
        return snDataStr2;
    }

    public void setSnDataStr2(String snDataStr2) {
        this.snDataStr2 = snDataStr2;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
}
