package top.itodouble.common.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.itodouble.common.pojo.excel.ExcelHeadVo;
import top.itodouble.common.utils.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelWriteUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelWriteUtils.class);

    /**
     * 生成excel
     *
     * @param xssfSheet
     * @param dataList
     * @param excelHeadVoList
     * @return
     */
    public static XSSFSheet writeLine(XSSFSheet xssfSheet, List<Map<String, Object>> dataList, List<ExcelHeadVo> excelHeadVoList) {
        Row row = xssfSheet.createRow(0);
        Cell cell;
        ExcelHeadVo excelHeadVo;
        Map<String, Object> rowData;
        for (int i = 0; i < excelHeadVoList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(excelHeadVoList.get(i).getName());
        }
        for (int i = 0; i < dataList.size(); i++) {
            row = xssfSheet.createRow(i + 1);
            rowData = dataList.get(i);
            for (int j = 0; j < excelHeadVoList.size(); j++) {
                excelHeadVo = excelHeadVoList.get(j);
                cell = row.createCell(j);
                CellValueUtils.setCellValue(xssfSheet, cell, excelHeadVo.getCellDateType(), excelHeadVo.getCellStyle(), rowData.get(excelHeadVo.getKey()));
            }
        }
        return xssfSheet;
    }

    public static XSSFSheet writeLine(XSSFSheet xssfSheet, List dataList, List<ExcelHeadVo> excelHeadVoList, Class clazz) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Row row = xssfSheet.createRow(0);
        Cell cell;
        ExcelHeadVo excelHeadVo;
        Object rowData;
        for (int i = 0; i < excelHeadVoList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(excelHeadVoList.get(i).getName());
        }
        for (int i = 0; i < dataList.size(); i++) {
            row = xssfSheet.createRow(i + 1);
            rowData = dataList.get(i);
            for (int j = 0; j < excelHeadVoList.size(); j++) {
                excelHeadVo = excelHeadVoList.get(j);
                cell = row.createCell(j);
                Object val = null;
                try {
                    Method getSnMtd = new PropertyDescriptor(excelHeadVo.getKey(), clazz).getReadMethod();
                    val = getSnMtd.invoke(rowData);
                } catch (Exception e) {
                    logger.error("key:" + excelHeadVo.getKey());
                    logger.error("val:" + val);
                    logger.error(e.getMessage(), e);
                }
                CellValueUtils.setCellValue(xssfSheet, cell, excelHeadVo.getCellDateType(), excelHeadVo.getCellStyle(), val);
            }
        }
        return xssfSheet;
    }

    /**
     * 在原有的基础上生成excel工作表 只填充原有头部没有的数据
     *
     * @param xssfSheet       工作表
     * @param startRow        数据填充开始行
     * @param headerRowIdx    表头行
     * @param dataList        填充数据列表 key为ExcelHeadVo中的key
     * @param excelHeadVoList 注意顺序 必须和原有excel顺序保持一致
     * @return
     */
    public static XSSFSheet writeLineWithOld(XSSFSheet xssfSheet, Integer startRow, Integer headerRowIdx, List<Map<String, Object>> dataList, List<ExcelHeadVo> excelHeadVoList) {
        //创建行
        XSSFRow xssfRow;
        //创建列，即单元格Cell
        XSSFCell xssfCell;
        // 每一行数据
        Map<String, Object> rowData;
        // 头部cell填充信息
        ExcelHeadVo headVo;

        XSSFRow headRow = xssfSheet.getRow(headerRowIdx);
        List<String> header = new ArrayList<>();
        Iterator headRowIterator = headRow.cellIterator();
        while (headRowIterator.hasNext()) {
            header.add(StringUtils.toString(headRowIterator.next()));
        }
        for (int i = 0; i < excelHeadVoList.size(); i++) {
            headVo = excelHeadVoList.get(i);
            if (!header.contains(headVo)) {
                xssfCell = headRow.createCell(i);
                xssfCell.setCellType(CellType.STRING);
                xssfCell.setCellValue(headVo.getName());
            }
        }
        for (int i = 0; i < dataList.size(); i++) {
            xssfRow = xssfSheet.getRow(i + startRow);
            rowData = dataList.get(i);
            for (int j = 0; j < excelHeadVoList.size(); j++) {
                headVo = excelHeadVoList.get(j);
                if (!header.contains(headVo.getName())) {
                    xssfCell = xssfRow.createCell(j);
                    CellValueUtils.setCellValue(xssfSheet, xssfCell, headVo.getCellDateType(), headVo.getCellStyle(), rowData.get(headVo.getKey()));
                }
            }
        }
        return xssfSheet;
    }


}
