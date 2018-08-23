import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    private static final Logger logger = LogManager.getLogger(DataSourceConfig.class);
    
    
    /**
     * Hikari DataConnectorPool
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @param cachePrepStmts
     * @param prepStmtCacheSize
     * @param prepStmtCacheSqlLimit
     * @return
     */
    @Bean
    public HikariDataSource dataSource(@Value("${jdbc.driver}") String driverClass,
                                      @Value("${jdbc.url}") String url,
                                      @Value("${jdbc.username}") String userName,
                                      @Value("${jdbc.password}") String password,
                                      @Value("${hikari.cachePrepStmts}") String cachePrepStmts,
                                      @Value("${hikari.prepStmtCacheSize}") int prepStmtCacheSize,
                                      @Value("${hikari.prepStmtCacheSqlLimit}") int prepStmtCacheSqlLimit,
                                      @Value("${hikari.connectionTestQuery}") String connectionTestQuery,
                                      @Value("${hikari.minIdle}") int minIdle,
                                      @Value("${hikari.maxPoolSize}") int maxPoolSize) {
        logger.info("DataSource");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(password);
        config.setDriverClassName(driverClass);
        config.setConnectionTestQuery(connectionTestQuery);
        config.setMinimumIdle(minIdle);
        config.setMaximumPoolSize(maxPoolSize);
        config.addDataSourceProperty("cachePrepStmts", cachePrepStmts);
        config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", prepStmtCacheSqlLimit);

        HikariDataSource hikariDataSource = new HikariDataSource(config);

        return hikariDataSource;
    }


    @Bean
    public DataSourceTransactionManager txManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager txManager) {
        return new TransactionTemplate(txManager);
    }





}
