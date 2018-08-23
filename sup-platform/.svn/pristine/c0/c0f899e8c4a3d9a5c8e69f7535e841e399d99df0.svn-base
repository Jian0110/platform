import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setLoadOnStartup(1);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding","utf-8");
        encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),true,"/*");

        /*
         * 
         
        FilterRegistration.Dynamic druidWebStatFilter = servletContext.addFilter("druidWebStatFilter",WebStatFilter.class);
        druidWebStatFilter.setInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        druidWebStatFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),true,"/*");

        ServletRegistration.Dynamic druidStatViewServlet = servletContext.addServlet("druidStatView", StatViewServlet.class);
        druidStatViewServlet.addMapping("/druid/*");
        */
        
        
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        delegatingFilterProxy.setTargetBeanName("shiroFilter");
        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilter", delegatingFilterProxy);
        shiroFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),true,"/*");

        //servletContext.setSessionTimeout(600);


    }
}
