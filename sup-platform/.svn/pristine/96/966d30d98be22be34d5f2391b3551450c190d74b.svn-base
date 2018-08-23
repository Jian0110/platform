package cn.com.ssj.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxUtils {
	private static final Logger logger = LogManager.getLogger(AjaxUtils.class);
	private static ObjectMapper mapper = new ObjectMapper();

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}

	public static void writeJson(Object value, HttpServletResponse response) {
		JsonGenerator jsonGenerator = null;
		try {
			jsonGenerator = mapper.getFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			if (jsonGenerator != null) {
				jsonGenerator.writeObject(value);
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}

	private AjaxUtils() {
	}

}
