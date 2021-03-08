package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

public enum SexEnum implements BaseEnum {
    MALE("male", 0,  "男"),
    FEMALE("female", 1, "女");

    private String sn;
    private Integer snData;
    private String snDesc;

    SexEnum(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }

    public static SexEnum getBySnDesc(String snDesc) {
        if (null!= snDesc){
            if (FEMALE.getSnData().equals(snDesc) || FEMALE.getSnDesc().equals(snDesc)){
                return FEMALE;
            }
            return MALE;
        }
        return null;
    }

    public static SexEnum getByData(Integer snData){
        if (null!= snData){
            if (FEMALE.snData == snData){
                return FEMALE;
            }
        }
        return MALE;
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
