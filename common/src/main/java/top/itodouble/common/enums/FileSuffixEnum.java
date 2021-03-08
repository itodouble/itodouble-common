package top.itodouble.common.enums;

import org.apache.commons.lang3.StringUtils;
import top.itodouble.common.utils.file.FileUtils;

/**
 * 文件 后缀名
 */
public enum FileSuffixEnum {
    PDF("pdf"),
    HTML("html");

    private String suffixName;

    FileSuffixEnum(String suffixName) {
        this.suffixName = suffixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public static Boolean is(FileSuffixEnum filesType, String fileName){
        if (StringUtils.isNotBlank(fileName)){
            String suffixName = FileUtils.getFileSuffix(fileName);
            if (StringUtils.isNotBlank(suffixName) && suffixName.toLowerCase().equals(filesType.getSuffixName())){
                return true;
            }
        }
        return false;
    }
}
