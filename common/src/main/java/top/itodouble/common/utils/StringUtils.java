package top.itodouble.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * 空字符串
	 */
	private static final String NULLSTR = "";

	/**
	 * 下划线
	 */
	private static final char SEPARATOR = '_';

	/**
	 * 首字母转小写
	 *
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}


	/**
	 * 首字母转大写
	 *
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * Object对象转换Integer
	 *
	 * @param obj
	 * @return
	 */
	public static Integer toInteger(Object obj) {
		return toInteger(obj, null);
	}

	/**
	 * 转换为int<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 *
	 * @param value        被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Integer toInteger(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		final String valueStr = toString(value, null);
		if (StringUtils.isEmpty(valueStr)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Object对象转换String
	 *
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		if (null != object) {
			try {
				if (object instanceof String) {
					return (String) object;
				}
				return object.toString();
			} catch (Exception e) {
				return object + "";
			}

		}
		return "";
	}

	/**
	 * Object对象转换String 为空时返回默认
	 *
	 * @param object
	 * @return
	 */
	public static String toString(Object object, String def) {
		if (null != object) {
			try {
				if (object instanceof String) {
					return (String) object;
				}
				return object.toString();
			} catch (Exception e) {
				return object + "";
			}

		}
		return def;
	}

	/**
	 * 判断Object对象是否为空(字符串)
	 *
	 * @param obj
	 * @return
	 */
	public static Boolean isNotNull(Object obj) {
		if (null == obj) {
			return false;
		}
		String str = toString(obj);
		if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
			if ("null".equals(str.toLowerCase()) || "[]".equals(str) || "{}".equals(str)) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 字符串分割 避免中文切割一半
	 *
	 * @param s
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String cutString(String s, int length) throws UnsupportedEncodingException {
		byte[] bytes = s.getBytes("Unicode");
		int n = 0;
		int i = 2;
		for (; i < bytes.length && n < length; i++) {
			if (i % 2 == 1) {
				n++;
			} else {
				if (bytes[i] != 0) {
					n++;
				}
			}
		}
		if (i % 2 == 1) {
			if (bytes[i - 1] != 0) {
				i = i - 1;
			} else {
				i = i + 1;
			}
		}
		return new String(bytes, 0, i, "Unicode");
	}

	/**
	 * object转字符串 最长250
	 *
	 * @return
	 */
	public static String parseToStringMax250(Object object) {
		String str = toString(object);
		if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
			return "";
		} else {
			if (str.length() > 250) {
				try {
					return cutString(str, 250);
				} catch (Exception e) {
					return "";
				}
			}
			return str;
		}
	}

	/**
	 * 替换字符串中 ${key} 循环map中key
	 *
	 * @param content
	 * @param map
	 * @return
	 */
	public static String replaceByMapKey(String content, Map<String, Object> map) {
		if (null != map && StringUtils.isNotNull(content)) {
			Set<Map.Entry<String, Object>> entrySet = map.entrySet();
			if (CollectionUtils.isNotEmpty(entrySet)) {
				for (Map.Entry<String, Object> each : entrySet) {
					content = content.replaceAll("\\$\\{" + each.getKey() + "}", StringUtils.toString(each.getValue()));
				}
			}
		}
		return content;
	}

	/**
	 * 替换字符串中 ${key} 根据content中"${"和"}"确定key
	 *
	 * @param content
	 * @param map
	 * @return
	 */
	public static String replaceByPrefix(String content, Map<String, Object> map) {
		if (null != map && StringUtils.isNotNull(content)) {
			boolean flag = true;
			int startIdx, endIdx = -1;
			String key;
			while (flag) {
				startIdx = content.indexOf("${");
				endIdx = content.indexOf("}");
				if (startIdx > -1 && endIdx > startIdx) {
					key = content.substring(startIdx + 2, endIdx);
					if (StringUtils.isNotNull(key)) {
						content = content.replaceAll("\\$\\{" + key + "}", StringUtils.toString(map.get(key)));
					}
				} else {
					flag = false;
				}
			}
		}
		return content;
	}

	/**
	 * * 判断一个对象数组是否为空
	 *
	 * @param objects 要判断的对象数组
	 *                * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects) {
		// return isNull(objects) || (objects.length == 0);
		return !isNotNull(objects) || (objects.length == 0);
	}

	/**
	 * 下划线转驼峰命名
	 */
	public static String toUnderScoreCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (i > 0) {
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			} else {
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1)) {
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
				sb.append(SEPARATOR);
			} else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 是否包含字符串
	 *
	 * @param str  验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 *
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param params   参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params) {
		if (isEmpty(params) || isEmpty(template)) {
			return template;
		}
		return StrFormatter.format(template, params);
	}

	/**
	 * 将对象转为字符串<br>
	 * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
	 *
	 * @param obj 对象
	 * @return 字符串
	 */
	public static String utf8Str(Object obj) {
		return str(obj, CharsetKit.CHARSET_UTF_8);
	}

	/**
	 * 将对象转为字符串<br>
	 * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组 2、对象数组会调用Arrays.toString方法
	 *
	 * @param obj     对象
	 * @param charset 字符集
	 * @return 字符串
	 */
	public static String str(Object obj, Charset charset) {
		if (null == obj) {
			return null;
		}

		if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof byte[] || obj instanceof Byte[]) {
			return str((Byte[]) obj, charset);
		} else if (obj instanceof ByteBuffer) {
			return str((ByteBuffer) obj, charset);
		}
		return obj.toString();
	}

	/**
	 * 转换为String数组<br>
	 *
	 * @param split 分隔符
	 * @param split 被转换的值
	 * @return 结果
	 */
	public static String[] toStrArray(String split, String str) {
		return str.split(split);
	}


	/**
	 * 转换为String数组<br>
	 *
	 * @param str 被转换的值
	 * @return 结果
	 */
	public static String[] toStrArray(String str) {
		return toStrArray(",", str);
	}

	/**
	 * * 判断一个对象数组是否非空
	 *
	 * @param objects 要判断的对象数组
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	public static void main(String[] args) {
		String content = "hello ${name} bey bey ${name}";
		Map<String, Object> map = new HashMap<>();
		map.put("name", "小兰");
		System.out.println(replaceByPrefix(content, map));
	}
}
