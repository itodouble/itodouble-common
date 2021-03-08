package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

public enum MessageStatusEnum implements BaseEnum {
    WAIT("wait", 0, "待发送"),
    SUCCESS("success", 1, "已发送"),
    FAILD("faild", 2, "发送失败"),
    CANCEL("cancel", 3, "取消发送");

    private String sn;
    private Integer snData;
    private String snDesc;

    MessageStatusEnum(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getSnData() {
        return snData;
    }

    public void setSnData(Integer snData) {
        this.snData = snData;
    }

    public String getSnDesc() {
        return snDesc;
    }

    public void setSnDesc(String snDesc) {
        this.snDesc = snDesc;
    }
}
