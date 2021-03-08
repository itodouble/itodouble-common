package top.itodouble.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "枚举转换实体")
public class EnumEntity implements Serializable {
    @ApiModelProperty(value = "枚举sn,关键字")
    private String sn;
    @ApiModelProperty(value = "枚举的值")
    private Object snData;
    @ApiModelProperty(value = "对于枚举的值描述")
    private String snDesc;
    /**
     * 补充数据
     */
    @ApiModelProperty(value = "补充数据")
    private String fval;

    public EnumEntity() {
    }

    public EnumEntity(String sn, Object snData, String snDesc) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
    }

    public EnumEntity(String sn, Object snData, String snDesc, String fval) {
        this.sn = sn;
        this.snData = snData;
        this.snDesc = snDesc;
        this.fval = fval;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Object getSnData() {
        return snData;
    }

    public void setSnData(Object snData) {
        this.snData = snData;
    }

    public String getSnDesc() {
        return snDesc;
    }

    public void setSnDesc(String snDesc) {
        this.snDesc = snDesc;
    }

    public String getFval() {
        return fval;
    }

    public void setFval(String fval) {
        this.fval = fval;
    }
}
