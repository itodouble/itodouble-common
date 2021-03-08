package top.itodouble.common.pojo.excel;


import org.apache.poi.ss.usermodel.CellType;

import java.io.Serializable;

/**
 * excel 每个单元格头信息
 */
public class ExcelHeadVo implements Serializable  {
    /**
     * 显示excel头
     */
    private String name;
    /**
     * 头对应的 excel key（map key）
     */
    private String key;
    /**
     * 数据类型
     */
    private CellType cellDateType;

    public ExcelHeadVo() {
    }

    public ExcelHeadVo(String name, String key, CellType cellDateType) {
        this.name = name;
        this.key = key;
        this.cellDateType = cellDateType;
    }

    /**
     * @return name 显示excel头
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name 显示excel头
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return key 头对应的 excel key（map key）
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key 头对应的 excel key（map key）
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return cellDateType 数据类型
     */
    public CellType getCellDateType() {
        return this.cellDateType;
    }

    /**
     * @param cellDateType 数据类型
     */
    public void setCellDateType(CellType cellDateType) {
        this.cellDateType = cellDateType;
    }
}
