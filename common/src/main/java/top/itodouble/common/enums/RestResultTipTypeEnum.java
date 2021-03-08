package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

public enum RestResultTipTypeEnum implements BaseEnum {
    ;
    private String sn;
    private String snData;
    private String snDesc;

    @Override
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String getSnData() {
        return snData;
    }

    public void setSnData(String snData) {
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
