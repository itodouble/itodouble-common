package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

/**
 * 消息发送类型 短信还是邮件
 */
public enum MessageSendTypeEnum implements BaseEnum {
    SITE("site", 0, "站内信"),
    EMAIL("email", 1, "邮件"),
    SMS("sms", 2, "短信");

    private String sn;
    private Integer snData;
    private String snDesc;

    public static MessageSendTypeEnum getSendType(Object type){
        if (null == type){
            return null;
        }
        if (type instanceof Integer){
            if (EMAIL.getSnData() == Integer.valueOf(type.toString())){
                return EMAIL;
            } else if (SMS.getSnData() == Integer.valueOf(type.toString())){
                return SMS;
            }
        } else if (type instanceof String){
            String t = type.toString();
            if (EMAIL.getSn().equals(t) || t.equals(EMAIL.snData+"")){
                return EMAIL;
            } else if (SMS.getSn().equals(t) || t.equals(SMS.snData+"")) {
                return SMS;
            }
        }
        return null;
    }

    MessageSendTypeEnum(String sn, Integer snData, String snDesc) {
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
