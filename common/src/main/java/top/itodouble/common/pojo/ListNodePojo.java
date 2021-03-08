package top.itodouble.common.pojo;

import java.io.Serializable;

/**
 * 暂存排序 封装统一bean
 */
public class ListNodePojo implements Serializable {
    /**
     * 类型
     */
    private Object key;
    /**
     * 存储结果 用来排序比较？
     */
    private Object val;
    /**
     * obj原始数据
     */
    private Object original;

    public ListNodePojo() {
    }

    public ListNodePojo(Object key, Object val) {
        this.key = key;
        this.val = val;
    }

    public ListNodePojo(Object key, Object val, Object original) {
        this.key = key;
        this.val = val;
        this.original = original;
    }


    /**
     * @return key 类型
     */
    public Object getKey() {
        return this.key;
    }

    /**
     * @param key 类型
     */
    public void setKey(Object key) {
        this.key = key;
    }

    /**
     * @return val 存储结果 用来排序比较？
     */
    public Object getVal() {
        return this.val;
    }

    /**
     * @param val 存储结果 用来排序比较？
     */
    public void setVal(Object val) {
        this.val = val;
    }

    /**
     * @return original obj原始数据
     */
    public Object getOriginal() {
        return this.original;
    }

    /**
     * @param original obj原始数据
     */
    public void setOriginal(Object original) {
        this.original = original;
    }
}
