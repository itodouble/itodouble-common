package top.itodouble.common.pojo;

import java.io.Serializable;
import java.util.List;


public class PageablePojo<E> implements Serializable {
    /**
     * 当前页 0
     */
    private Integer pageNum;
    private Integer pageIndex;
    /**
     * 分页大小 10
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer count;
    private List<E> content;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc = "asc";

    public PageablePojo() {

    }

    public PageablePojo(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageablePojo(Integer pageNum, Integer pageSize, Integer count) {
        this(pageNum, pageSize);
        this.count = count;
    }

    public PageablePojo(Integer pageNum, Integer pageSize, Integer count, List<E> content) {
       this(pageNum, pageSize, count);
       this.content = content;
    }

    public Integer getPageNum() {
        if (null == pageNum || pageNum<0) {
            pageNum = 0;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if(null!=pageSize && 0<pageSize){
            return pageSize;
        }
        return 10;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<E> getContent() {
        return content;
    }

    public void setContent(List<E> content) {
        this.content = content;
    }

    public Integer getPageIndex() {
        if(null!=pageIndex && 0<pageIndex){
            return pageIndex;
        }
        return 1;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
