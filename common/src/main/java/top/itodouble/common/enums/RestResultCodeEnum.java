package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

public enum RestResultCodeEnum implements BaseEnum {
    SUCCESS("Y", 1, "请求成功"),
    ERROR("N", 0, "请求失败");

    private String sn;
    private Integer snData;
    private String snDesc;

    RestResultCodeEnum(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
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
