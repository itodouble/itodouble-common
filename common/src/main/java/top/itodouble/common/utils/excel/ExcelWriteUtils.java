package top.itodouble.common.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.itodouble.common.pojo.excel.ExcelHeadVo;
import top.itodouble.common.utils.StringUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

public class ExcelWriteUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelWriteUtils.class);

    /**
     * 生成excel
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
                setCellValue(xssfSheet, cell, excelHeadVo.getCellDateType(), rowData.get(excelHeadVo.getKey()));
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
        Integer oldHeaderLength = header.size();
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
                    setCellValue(xssfSheet,xssfCell, headVo.getCellDateType(), rowData.get(headVo.getKey()));
                }
            }
        }
        return xssfSheet;
    }

    public static void setCellValue(XSSFSheet xssfSheet, Cell cell, CellType cellType, Object val) {
        logger.debug(cell.toString());
        cell.setCellType(cellType);
        if (null != val) {
            if (cellType == CellType.NUMERIC) {
                if (val instanceof Date || val instanceof Timestamp || val instanceof java.sql.Date || val instanceof Time) {
                    XSSFCellStyle cellStyle = xssfSheet.getWorkbook().createCellStyle();
                    XSSFDataFormat format = xssfSheet.getWorkbook().createDataFormat();
                    cellStyle.setDataFormat(format.getFormat("yyyy-m-d"));
                    cell.setCellStyle(cellStyle);
                    if (val instanceof Date) {
                        cell.setCellValue((Date) val);
                    } else if (val instanceof Timestamp) {
                        cell.setCellValue((Timestamp) val);
                    } else if (val instanceof java.sql.Date) {
                        cell.setCellValue((java.sql.Date) val);
                    } else if (val instanceof Time) {
                        cell.setCellValue((Time) val);
                    }

                } else {
                    if (val instanceof Integer) {
                        cell.setCellValue((Integer) val);
                    } else if (val instanceof String) {
                        cell.setCellValue((String) val);
                    } else {
                        cell.setCellValue(Double.parseDouble(val.toString()));
                    }
                }
            } else if (cellType == CellType.FORMULA) {
                cell.setCellValue((RichTextString) val);
            } else if (cellType == CellType.BOOLEAN) {
                cell.setCellValue((Boolean) val);
            } else {
                cell.setCellValue((String) val);
            }
        }
    }
}
