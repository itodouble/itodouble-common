package top.itodouble.common.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.itodouble.common.pojo.excel.ExcelHeadVo;
import top.itodouble.common.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReadUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelReadUtils.class);

    /**
     * 读取工作表中数据
     *
     * @param sheet         工作表
     * @param excelHeadList 列对应数据 顺序必须保证
     * @param dataStart     开始读行
     * @param dataEnd       结束读行（可以为空）
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> readStringExcelList(Sheet sheet, List<ExcelHeadVo> excelHeadList, Integer dataStart, Integer dataEnd) throws IOException {
        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, Object> map = null;
        if (null == dataStart || dataStart < 0) {
            dataStart = 0;
        }
        if (null == dataEnd || dataEnd < 0) {
            dataEnd = sheet.getLastRowNum();
        }
        if (dataEnd < dataStart) {
            dataEnd = dataStart;
        }
        Row row = null;
        Cell cell = null;
        ExcelHeadVo excelHeadVo = null;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            logger.debug("row num:{}", i);
            if (i >= dataStart && i <= dataEnd) {
                row = sheet.getRow(i);
                map = new HashMap<>();
                for (int j = 0; j < excelHeadList.size(); j++) {
                    excelHeadVo = excelHeadList.get(j);
                    logger.debug("cell num:{}, excelHeadKey", j, excelHeadVo.getKey());
                    if (row.getLastCellNum() <= (j + 1)) {
                        cell = row.getCell(j);
                        if (null != cell) {
                            map.put(excelHeadVo.getKey(), CellValueUtils.getCellValue(cell));
                        }
                    }
                }
                result.add(map);
            }
        }
        return result;
    }


    /**
     * 检查excel标题
     *
     * @param sheet
     * @return
     */
    public static Map<String, Object> checkExcelHeader(Sheet sheet, List<ExcelHeadVo> excelHeadList, Integer headerIndex) throws IOException {
        List<String> errorMsg = new ArrayList<>();
        Row row = sheet.getRow(headerIndex);
        Boolean passFlag = true; // 全部检验通过
        Boolean columnPassFlag = null; // 列
        Map<String, Object> resMap = new HashMap<>();
        ExcelHeadVo headVo = null;
        Cell cell = null;
        for (int i = 0; i < excelHeadList.size(); i++) {
            headVo = excelHeadList.get(i);
            columnPassFlag = true;
            if (null != headVo) {
                if (i < row.getLastCellNum()) {
                    cell = row.getCell(i);
                    if (null != cell) {
                        if (!cell.getStringCellValue().trim().equals(headVo.getName())) {
                            columnPassFlag = false;
                        }
                    }
                } else {
                    columnPassFlag = false;
                }
            }
            if (!columnPassFlag) {
                passFlag = false;
                errorMsg.add(headVo.getName());
            }
        }
        resMap.put("checkHeaderFlag", passFlag);
        resMap.put("errorMsg", errorMsg);
        return resMap;
    }

    private ExcelHeadVo getCellHeaderPojoByName(List<ExcelHeadVo> excelHeadList, Object headerName) {
        if (StringUtils.isNotNull(headerName)) {
            for (ExcelHeadVo each : excelHeadList) {
                if (each.getName().equals(headerName)) {
                    return each;
                }
            }
        }
        return null;
    }
}
