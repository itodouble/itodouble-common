package top.itodouble.common.enums;

/**
 * email message contentType
 */
public enum ContentTypeEnum {
    DOCX("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    DOC("doc", "application/msword"),
    PDF("pdf", "application/pdf"),

    XHTML("xhtml", "application/xhtml+xml"),
    HTML("html", "text/html"),
    HTX("htx", "text/html"),
    HTM("htm", "text/html"),
    HTT("htt", "text/webviewhtml"),
    JSP("jsp", "text/html"),
    XHTML_TEXT("xhtml", "text/html"),

    DOTX("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template"),
    XLS("xls", "application/vnd.ms-excel"),
    DOT("dot", "application/msword"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PPTX("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    JAVA("java", "java/*"),
    CLASS("class", "java/*"),

    PNG_APPLICATION("png", "application/x-png"),
    PNG("png", "image/png"),
    GIF("gif", "image/gif"),
    ICO("ico", "image/x-icon"),
    JPE("jpe", "image/jpeg"),
    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    WBMP("wbmp", "image/vnd.wap.wbmp"),

    DWF("dwf", "model/vnd.dwf"),
    AVI("avi", "video/avi"),
    MP4("mp4", "video/mpeg4"),
    MP4_AUDIO("mp4", "audio/mp4"),
    WMV("wmv", "video/x-ms-wmv"),
    MP3("mp3", "audio/mp3");

    private String sn;
    private String snData;
    private String desc;

    ContentTypeEnum(String sn, String snData) {
        this.sn = sn;
        this.snData = snData;
    }

    ContentTypeEnum(String sn, String snData, String desc) {
        this.sn = sn;
        this.snData = snData;
        this.desc = desc;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSnData() {
        return snData;
    }

    public void setSnData(String snData) {
        this.snData = snData;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
