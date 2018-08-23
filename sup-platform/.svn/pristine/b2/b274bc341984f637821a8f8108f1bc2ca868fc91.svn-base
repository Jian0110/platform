package cn.com.ssj.controller;//package cn.com.ssj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.com.ssj.dto.ResultObject;
import cn.com.ssj.utils.AjaxUtils;

@ControllerAdvice
public class SpringExceptionHandler {

    private static final Logger logger = LogManager.getLogger(SpringExceptionHandler.class);

    @ExceptionHandler(value = {BindException.class})
    public ModelAndView validExceptionHandler(BindException e, WebRequest request, HttpServletResponse response) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        if (AjaxUtils.isAjaxRequest(request)) {
            List<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
            fieldErrors.forEach(error -> {
                HashMap<String, String> errorObject = new HashMap<String, String>();

                String field = error.getField();
                String message = error.getDefaultMessage();
                errorObject.put("field", field);
                errorObject.put("message", message);
                errorList.add(errorObject);

            });
            ResultObject resultObject = ResultObject.NACK(errorList.get(0).get("message"), errorList);
            AjaxUtils.writeJson(resultObject, response);
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionMessage", e.toString());
        modelAndView.addObject("exceptionStackTrace", e.getStackTrace());
        return modelAndView;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ModelAndView validExceptionHandler(MethodArgumentNotValidException e, WebRequest request, HttpServletResponse response) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        if (AjaxUtils.isAjaxRequest(request)) {
            List<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
            fieldErrors.forEach(error -> {
                HashMap<String, String> errorObject = new HashMap<String, String>();

                String field = error.getField();
                String message = error.getDefaultMessage();
                errorObject.put("field", field);
                errorObject.put("message", message);
                errorList.add(errorObject);

            });
            ResultObject resultObject = ResultObject.NACK(errorList.get(0).get("message"));
            AjaxUtils.writeJson(resultObject, response);
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionMessage", e.toString());
        modelAndView.addObject("exceptionStackTrace", e.getStackTrace());
        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = UnauthorizedException.class)
    public ModelAndView unauthorizedException(UnauthorizedException e, WebRequest request, HttpServletResponse response) {
		/*
		if (AjaxUtils.isAjaxRequest(request)) {
			ResultObject resultObject = ResultObject.NACK("权限不足");
			AjaxUtils.writeJson(resultObject, response);
			return null;
		}
		*/
        ModelAndView modelAndView = new ModelAndView("403");
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception e, WebRequest request, HttpServletResponse response) {
        logger.error("========================异常开始=======================");
        e.printStackTrace();
        logger.error("========================异常结束=======================");
        if (AjaxUtils.isAjaxRequest(request)) {
            ResultObject resultObject = ResultObject.NACK(e.toString() + e.getStackTrace());
            AjaxUtils.writeJson(resultObject, response);
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exceptionMessage", e.toString());
        modelAndView.addObject("exceptionStackTrace", e.getStackTrace());
        return modelAndView;
    }
}
