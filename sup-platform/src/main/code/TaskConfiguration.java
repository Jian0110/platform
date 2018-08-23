import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class TaskConfiguration {
    private static final Logger logger = LogManager.getLogger(TaskConfiguration.class);

    @Value("${upload.save.path.base}")
    String uploadSaveFilePath;
    @Value("${upload.temporary.path.base}")
    String uploadTemporaryFilePath;


    /*
    @Async
    @Scheduled(cron = "0 10 1 * * ?")
    public void jobSchedule() {

    }
    */

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        return executor;
    }
}