package top.itodouble.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import top.itodouble.common.enums.RestResultCodeEnum;

import java.io.Serializable;

@ApiModel(value = "基础返回类")
public class RestResult<T> implements Serializable {
    @ApiModelProperty(value = "具体返回数据")
    private T data;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息描述")
    private String message;
    @ApiModelProperty(value = "弹窗提醒类型")
    private String tipType;
    @ApiModelProperty(value = "当分页时 分页信息")
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private PageInfo pageInfo;

    public static RestResult success(){
        RestResult restResult = new RestResult(RestResultCodeEnum.SUCCESS.getSnData());
        return restResult;
    }

    public static RestResult error(){
        RestResult restResult = new RestResult(RestResultCodeEnum.ERROR.getSnData());
        return restResult;
    }

    public RestResult data(T data){
        this.data = data;
        return this;
    }

    public RestResult message(String message) {
        this.message = message;
        return this;
    }

    public RestResult code(Integer code) {
        this.code = code;
        return this;
    }

    public RestResult tipType(String tipType) {
        this.tipType = tipType;
        return this;
    }

    public RestResult pageInfo(PageInfo pageInfo){
        this.data = (T) pageInfo.getList();
        this.pageInfo = pageInfo;
        return this;
    }

    public RestResult() {
    }

    public RestResult(Integer code) {
        this.code = code;
    }

    public RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResult(T data, Integer code, String message, String tipType) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.tipType = tipType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTipType() {
        return tipType;
    }

    public void setTipType(String tipType) {
        this.tipType = tipType;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
