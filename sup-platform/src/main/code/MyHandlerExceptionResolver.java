

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("==============异常开始=============");
        e.printStackTrace();
        System.out.println("==============异常结束=============");
        ModelAndView mv = new ModelAndView("error");
        //mv.addObject("exception", e.toString().replaceAll("\n", "<br/>"));
        mv.addObject("exceptionMessage", e.toString());
        mv.addObject("exceptionStackTrace", e.getStackTrace());
        return mv;
    }
}
