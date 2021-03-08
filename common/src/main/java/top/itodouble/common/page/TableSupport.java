package top.itodouble.common.page;


import top.itodouble.common.pojo.PageablePojo;
import top.itodouble.common.utils.spring.ServletUtils;

/**
 * 表格数据处理
 *
 */
public class TableSupport {
	/**
	 * 当前记录起始索引
	 */
	public static final String PAGE_NUM = "pageNum";

	/**
	 * 每页显示记录数
	 */
	public static final String PAGE_SIZE = "pageSize";
	public static final String LIMIT = "limit";

	/**
	 * 排序列
	 */
	public static final String ORDER_BY_COLUMN = "orderByColumn";

	/**
	 * 排序的方向 "desc" 或者 "asc".
	 */
	public static final String IS_ASC = "isAsc";

	/**
	 * 封装分页对象
	 */
	public static PageablePojo getPageDomain() {
        PageablePojo pageDomain = new PageablePojo();
		pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
		pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
		if (null == pageDomain.getPageSize()) {
			pageDomain.setPageSize(ServletUtils.getParameterToInt(LIMIT));
		}
		pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
		pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
		return pageDomain;
	}

	public static PageablePojo buildPageRequest() {
		return getPageDomain();
	}
}
