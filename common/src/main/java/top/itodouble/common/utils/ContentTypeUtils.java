package top.itodouble.common.utils;

import org.apache.commons.lang3.StringUtils;
import top.itodouble.common.enums.ContentTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName ContentTypeUtils
 * @Author lanshunbin
 * @date 2020.04.17 11:05
 */
public class ContentTypeUtils {
    public static List<ContentTypeEnum> getDocType() {
        List<ContentTypeEnum> list = new ArrayList<>();
        list.add(ContentTypeEnum.DOC);
        list.add(ContentTypeEnum.DOCX);
        return list;
    }

    public static List<ContentTypeEnum> getPdfType() {
        List<ContentTypeEnum> list = new ArrayList<>();
        list.add(ContentTypeEnum.PDF);
        return list;
    }

    public static List<ContentTypeEnum> getHtmlType() {
        List<ContentTypeEnum> list = new ArrayList<>();
        list.add(ContentTypeEnum.HTM);
        list.add(ContentTypeEnum.HTML);
        list.add(ContentTypeEnum.HTT);
        list.add(ContentTypeEnum.HTX);
        list.add(ContentTypeEnum.XHTML);
        list.add(ContentTypeEnum.XHTML_TEXT);
        list.add(ContentTypeEnum.JSP);
        return list;
    }

    public static Boolean isDocOrPdf(String type) {
        if (StringUtils.isBlank(type)) {
            return false;
        }
        List<ContentTypeEnum> list = getDocType();
        list.addAll(getPdfType());
        type = type.toLowerCase();
        for (ContentTypeEnum contentTypeEnum : list) {
            if (type.indexOf(contentTypeEnum.getSnData()) > -1 || contentTypeEnum.getSn().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isDocOrPdfOrhtml(String type) {
        if (StringUtils.isBlank(type)) {
            return false;
        }
        List<ContentTypeEnum> list = getDocType();
        list.addAll(getPdfType());
        list.addAll(getHtmlType());
        type = type.toLowerCase();
        for (ContentTypeEnum contentTypeEnum : list) {
            if (type.indexOf(contentTypeEnum.getSnData()) > -1 || contentTypeEnum.getSn().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
