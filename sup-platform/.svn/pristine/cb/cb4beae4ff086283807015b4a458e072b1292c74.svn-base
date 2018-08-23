import javax.sql.DataSource;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;


/**
 * 
 * @author immortals
 *
 */
@Configuration
@Import(DataSourceConfig.class)
public class MyBatisConfig {
	private static final Logger logger = LogManager.getLogger(MyBatisConfig.class);
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(
            DataSourceTransactionManager transactionManager,
    		DataSource dataSource, 
    		@Value("${mybatis.helperDialect}") String helperDialect) {
    	logger.info("sqlSessionFactory");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(Log4j2Impl.class);
        configuration.setCacheEnabled(true);
        configuration.setUseGeneratedKeys(true); 
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultStatementTimeout(300);
        
        Properties properties = new Properties();
        //properties.setProperty("reasonable","true");
        properties.setProperty("helperDialect",helperDialect);
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.com.ssj.mybatis.model");
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean;
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	logger.info("mapperScannerConfigurer");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.com.ssj.mybatis.mapper");
        return mapperScannerConfigurer;
    }
}
