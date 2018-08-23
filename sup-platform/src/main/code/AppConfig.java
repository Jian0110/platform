import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@PropertySources(value = { @PropertySource("classpath:db.properties"), @PropertySource("classpath:shiro.properties") ,@PropertySource("classpath:app.properties")})
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@Import({ CachingConfig.class, MyBatisConfig.class, ShiroConfig.class ,TaskConfiguration.class})
@Import({ ShiroConfig.class ,TaskConfiguration.class})
@ComponentScan(value = {"cn.com.ssj.controller","cn.com.ssj.mybatis.mapper","cn.com.ssj.service","cn.com.ssj.shiro"})
public class AppConfig implements WebMvcConfigurer {
	private static final Logger logger = LogManager.getLogger(AppConfig.class);

	@Value("${upload.save.path.base}") 
	private String uploadSavePathBase;
	@Value("${upload.temporary.path.base}") 
	private String uploadTemporaryPathBase;
	
	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		logger.info("setupViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		// resolver.setOrder(1);
		return resolver;
	}

	/*
	 * @Bean public FreeMarkerViewResolver viewResolverFtl() {
	 * FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
	 * viewResolver.setViewClass(FreeMarkerView.class);
	 * viewResolver.setContentType("text/html; charset=UTF-8");
	 * viewResolver.setExposeRequestAttributes(true);
	 * viewResolver.setExposeSessionAttributes(true);
	 * viewResolver.setExposeSpringMacroHelpers(true); viewResolver.setCache(false);
	 * viewResolver.setSuffix(".ftl"); viewResolver.setPrefix("/WEB-INF/ftl/");
	 * viewResolver.setRequestContextAttribute("requestContext");
	 * viewResolver.setOrder(0); return viewResolver; }
	 * 
	 * @Bean public FreeMarkerConfig freeMarkerConfig(){
	 * freemarker.template.Configuration tplCfg = new
	 * freemarker.template.Configuration(freemarker.template.Configuration.
	 * VERSION_2_3_23); tplCfg.setDefaultEncoding("UTF-8");
	 * tplCfg.setOutputEncoding("UTF-8");
	 * tplCfg.setLocale(Locale.SIMPLIFIED_CHINESE);
	 * tplCfg.setDateFormat("yyyy-MM-dd"); tplCfg.setTimeFormat("HH:mm:ss");
	 * tplCfg.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
	 * tplCfg.setClassicCompatible(true);//空串显示"" tplCfg.setTemplateLoader(new
	 * WebappTemplateLoader(servletContext)); tplCfg.setNumberFormat("0.##");
	 * tplCfg.setBooleanFormat("true,false"); tplCfg.setSharedVariable("shiro", new
	 * ShiroTags()); tplCfg.setTemplateUpdateDelayMilliseconds(0);
	 * 
	 * FreeMarkerConfigurer fmCfg=new FreeMarkerConfigurer();
	 * fmCfg.setConfiguration(tplCfg); return fmCfg; }
	 * 
	 */

	/**
	 * 异常拦截
	 * 
	 * @return
	 */
	/*
	 * @Bean(name = "exceptionResolver") public HandlerExceptionResolver
	 * handlerExceptionResolver() { logger.info("handlerExceptionResolver"); return
	 * new MyHandlerExceptionResolver(); }
	 */

	/**
	 * 上传支持
	 * 
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {
		logger.info("commonsMultipartResolver");
		return new CommonsMultipartResolver();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("addResourceHandlers");
		registry.addResourceHandler("/**","/favicon.ico").addResourceLocations("/statics/","/favicon.ico","file:"+uploadSavePathBase,"file:"+uploadTemporaryPathBase);
	}

	@Bean
	public MessageSource messageSource() {
		logger.info("MessageSource");
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}
	

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();  
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("ValidationMessages");
		messageSource.setDefaultEncoding("UTF-8");
	    localValidatorFactoryBean.setValidationMessageSource(messageSource);  
	    return localValidatorFactoryBean;  
	}

	
	
	
	
	
	

}
