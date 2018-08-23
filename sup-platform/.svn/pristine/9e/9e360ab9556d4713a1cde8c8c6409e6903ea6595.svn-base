package cn.com.ssj.dto;

public class ResultObject {
	public static final String ACK = "ACK";
	public static final String NACK = "NACK";

	private final String code;
	
	private String message;
	
	private Object data;

	public String getCode() {
		return code;
	}

	private ResultObject(String code){
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	public static ResultObject ACK() {
		return ACK(null);
	}
	
	public static ResultObject ACK(String message) {
		return ACK(message,null);
	}

	public static ResultObject ACK(Object data) {
		return ACK(null,data);
	}

	public static ResultObject ACK(String message,Object data) {
		ResultObject result = new ResultObject(ACK);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
	
	public static ResultObject NACK() {
		return NACK(null);
	}

	public static ResultObject NACK(String message) {
		return NACK(message,null);
	}
	public static ResultObject NACK(Object data) {
		return NACK(null,data);
	}

	public static ResultObject NACK(String message,Object data) {
		ResultObject result = new ResultObject(NACK);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
	
}
