package top.itodouble.common.utils;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public class PageUtils {

    public static void copyPageInfo(PageInfo pageInfo,PageInfo newPageInfo){
        newPageInfo.setPageNum(pageInfo.getPageNum());
        newPageInfo.setPageSize(pageInfo.getPageSize());
        newPageInfo.setSize(pageInfo.getSize());
        newPageInfo.setStartRow(pageInfo.getStartRow());
        newPageInfo.setEndRow(pageInfo.getEndRow());
        newPageInfo.setPages(pageInfo.getPages());
        newPageInfo.setPrePage(pageInfo.getPrePage());
        newPageInfo.setNextPage(pageInfo.getNextPage());
        newPageInfo.setIsFirstPage(pageInfo.isIsFirstPage());
        newPageInfo.setIsLastPage(pageInfo.isIsLastPage());
        newPageInfo.setHasPreviousPage(pageInfo.isHasPreviousPage());
        newPageInfo.setHasNextPage(pageInfo.isHasNextPage());
        newPageInfo.setNavigatePages(pageInfo.getNavigatePages());
        newPageInfo.setNavigatepageNums(pageInfo.getNavigatepageNums());
        newPageInfo.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        newPageInfo.setNavigateLastPage(pageInfo.getNavigateLastPage());
        newPageInfo.setTotal(pageInfo.getTotal());
    }

    public static Integer getPage(Map map) {
        if (null != map.get("page")){
            return ObjectUtils.toInteger(map.get("page"), 1);
        }
        if (null != map.get("pageNum")){
            return ObjectUtils.toInteger(map.get("pageNum"), 1);
        }
        return 1;
    }

    public static Integer getPageSize(Map map) {
        if (null != map.get("pageSize")){
            return ObjectUtils.toInteger(map.get("pageSize"), 10);
        }
        return 10;
    }
}
