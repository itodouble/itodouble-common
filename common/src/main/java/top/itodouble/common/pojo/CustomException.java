package top.itodouble.common.pojo;


import top.itodouble.common.Base.BaseEnum;

public class CustomException extends RuntimeException{
	private Object code;
	private String  exMsg;

	public CustomException(Object code, String exMsg) {
		this.exMsg = exMsg;
		this.code = code;
	}

	public CustomException(BaseEnum baseEnum) {
		this.exMsg = baseEnum.getSnDesc();
		this.code = baseEnum.getSnData();
	}

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}
}
