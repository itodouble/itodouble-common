package top.itodouble.common.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class CellValueUtils {
    private static Logger logger = LoggerFactory.getLogger(CellValueUtils.class);


    /**
     * 获取单元格值
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        logger.debug("cell type:{}", cell.getCellTypeEnum());
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            // 数字格式日期
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
            return cell.getNumericCellValue();
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
            logger.debug("cell FORMULA :{}", cell.getCellFormula());
            return cell.getRichStringCellValue().getString();
        }
        return cell.getStringCellValue();
    }

    public static void setCellValue(XSSFSheet xssfSheet, Cell cell, CellType cellType, CellStyle style, Object val) {
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
            if (null != style) {
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 设置单元格值
     * @param sheet
     * @param cell
     * @param cellType
     * @param val
     */
    public static void setCellValue(Sheet sheet, Cell cell, CellType cellType, Object val) {
        logger.debug(cell.toString());
        cell.setCellType(cellType);
        if (null != val) {
            if (cellType == CellType.NUMERIC) {
                if (val instanceof Date || val instanceof Timestamp || val instanceof java.sql.Date || val instanceof Time) {
                    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                    DataFormat format = sheet.getWorkbook().createDataFormat();
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

    public static void setCellValue(Sheet sheet, Cell cell, CellType cellType, CellStyle style, Object val) {
        logger.debug(cell.toString());
        cell.setCellType(cellType);
        if (null != val) {
            if (cellType == CellType.NUMERIC) {
                if (val instanceof Date || val instanceof Timestamp || val instanceof java.sql.Date || val instanceof Time) {
                    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                    DataFormat format = sheet.getWorkbook().createDataFormat();
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
            if (null != style) {
                cell.setCellStyle(style);
            }
        }
    }
}
