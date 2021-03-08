package top.itodouble.common.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseEntity implements Serializable {
    @ApiModelProperty(name = "id" , value = "")
    private String id;

    @ApiModelProperty(name = "createUserId" , value = "创建人")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(name = "createTime" , value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "updateUserId" , value = "修改人")
    private String updateUserId;

    @ApiModelProperty(name = "updateUserName" , value = "修改人姓名")
    private String updateUserName;

    @ApiModelProperty(name = "updateTime" , value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    /**
     * 请求参数
     */
    @JsonIgnore
    private Map<String, Object> params;

    public BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getPageNum() {
        if (null == pageNum) {
            pageNum = 0;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if (null == pageSize) {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
