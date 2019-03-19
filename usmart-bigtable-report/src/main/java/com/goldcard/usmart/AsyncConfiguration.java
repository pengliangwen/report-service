package com.goldcard.usmart;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.goldcard.usmart.dao.mysql.ReportExportTaskMapper;
import com.goldcard.usmart.domain.report.TaskStatusEnum;

/**
 * @author 2299
 * @version 1.0 2018-11-08
 */
@EnableAsync
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {

  @Resource
  private ReportExportTaskMapper taskMapper;
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  @Bean("threadPoolTaskExecutor")
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int coreSize = Runtime.getRuntime().availableProcessors();
    executor.setCorePoolSize(coreSize / 3);
    executor.setMaxPoolSize(coreSize / 3 * 2);
    executor.setQueueCapacity(coreSize * 32);
    // 等待队列满了之后抛出 RejectedExecutionException
    executor.setRejectedExecutionHandler(new AbortPolicy());
    executor.setThreadNamePrefix("export-task-");
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new ReportExportExceptionHandler();
  }

  class ReportExportExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
      log.error("Throwable:{} Method:{} params:{}", ex.toString(), method.getName(),
          Arrays.asList(params));
      if (method.getName().equals("generate") && params[1] instanceof Long) {
        taskMapper.updateTaskStatus((Long) params[1], TaskStatusEnum.MISTAKE.getStatus(), null);
      }
    }
  }
}
