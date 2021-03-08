package top.itodouble.common.enums;


import top.itodouble.common.Base.BaseEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程处理状态
 */
public enum FlowStatusEnum implements BaseEnum {
    DSP("dsp", 0, "待申请"),
    SPZ("spz", 1, "审批中"),
    BH("bh", 2, "驳回"),
    ZZ("zz", 3, "终止"),
    BJ("bj", 4, "办结"),
    CG("cg", 5, "草稿"),
    CH("ch", 6, "撤回"),
    TJSB("tjsb", 9, "提交失败");

    private String sn;
    private Integer snData;
    private String snDesc;

    FlowStatusEnum(String sn, Integer snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }

    public static FlowStatusEnum getBySnData(Integer snData){
        if (null != snData){
            for (FlowStatusEnum flowStatusEnum: FlowStatusEnum.values()){
                if (flowStatusEnum.snData == snData){
                    return flowStatusEnum;
                }
            }
        }
        return null;
    }

    public static String translate(Short flowStatus, String def){
        if (null!=flowStatus){
            for (FlowStatusEnum flowStatusEnum: FlowStatusEnum.values()) {
                if (flowStatusEnum.getShortData() == flowStatus){
                    return flowStatusEnum.snDesc;
                }
            }
        }
        return def;
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

    /**
     * 正在审批中 没有办结或终止
     * @return
     */
    public static List<Short> inFlow(){
        List<Short> list = new ArrayList<>();
        list.add(SPZ.getShortData());
        list.add(BH.getShortData());
        list.add(CH.getShortData());
        return list;
    }

    /**
     * 还没有结束呢
     * @return
     */
    public static List<Short> notDone(){
        List<Short> list = new ArrayList<>();
        list.add(DSP.getShortData());
        list.add(SPZ.getShortData());
        list.add(BH.getShortData());
        list.add(CG.getShortData());
        list.add(CH.getShortData());
        list.add(TJSB.getShortData());
        return list;
    }

    /**
     * 正在审批中 没有办结或终止
     * @param flowStatus
     * @return
     */
    public static Boolean inFlow(Short flowStatus){
        if (null!=flowStatus){
            if (SPZ.getShortData() == flowStatus || BH.getShortData()==flowStatus || CH.getShortData()==flowStatus){
                return true;
            }
        }
        return false;
    }

    /**
     * 是结束状态 终止或办结
     * @param flowStatus
     * @return
     */
    public static Boolean inJieShu(Short flowStatus) {
        if (null!=flowStatus){
            if (ZZ.getSnData().equals(flowStatus) || BJ.getSnData().equals(flowStatus)){
                return true;
            }
        }
        return false;
    }
}
