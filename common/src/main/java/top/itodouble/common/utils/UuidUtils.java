package top.itodouble.common.utils;

import java.util.UUID;

public class UuidUtils {

	public static String gen() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}