package top.itodouble.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * 生成注释
 */
public class MybatisCommentGenerator implements CommentGenerator {
    private Properties properties = new Properties();
    private Properties systemPro = System.getProperties();
    private boolean suppressDate = false;
    private boolean suppressAllComments = false;
    private String currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

    public MybatisCommentGenerator() {
    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    public void addComment(XmlElement xmlElement) {
    }

    public void addRootComment(XmlElement rootElement) {
    }

    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
    }

    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    protected String getDateString() {
        String result = null;
        if (!this.suppressDate) {
            result = this.currentDateStr;
        }

        return result;
    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerEnum.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerEnum.addJavaDocLine(sb.toString());
            innerEnum.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressAllComments && StringUtils.isNotBlank(introspectedColumn.getRemarks())) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedColumn.getRemarks());
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }

    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        method.addJavaDocLine("/**");

        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        sb.setLength(0);
        sb.append(" * @return ");
        sb.append(introspectedColumn.getActualColumnName());
        sb.append(" ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        //		addJavadocTag(method, false);

        method.addJavaDocLine(" */");
    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }


        method.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        sb.append(" ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());

        //		addJavadocTag(method, false);

        method.addJavaDocLine(" */");
    }

    /**
     * 内部类
     * @param innerClass
     * @param introspectedTable
     */
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" ");
        sb.append(getDateString());
        innerClass.addJavaDocLine(sb.toString());
        innerClass.addJavaDocLine(" */");
    }

    /**
     * 内部类
     * @deprecated
     */
    @Deprecated
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString());

        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(systemPro.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateStr);

        //		addJavadocTag(innerClass, markAsDoNotDelete);

        innerClass.addJavaDocLine(" */");
    }

    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * "+introspectedTable.getRemarks());

        topLevelClass.addJavaDocLine(" * " + currentDateStr);
        topLevelClass.addJavaDocLine(" */");
    }

    public static String toLowerCaseFirstOne(String s) {
        if (null != s && !"".equals(s)) {
            return Character.isLowerCase(s.charAt(0)) ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1);
        } else {
            return null;
        }
    }

    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        System.out.println(method.getName());
    }

    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        System.out.println(method.getName());
    }

    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        System.out.println(field.getName());
    }

    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
    }

    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        System.out.println(innerClass);
    }

}
