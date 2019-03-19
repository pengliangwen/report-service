package com.goldcard.usmart.service;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.goldcard.usmart.dao.mysql.ReportExportTaskMapper;
import com.goldcard.usmart.domain.report.TaskStatusEnum;

/**
 * @author 2299
 * @version 1.0 2018-11-09
 */
@Component
public class ReportLazyCleaner {

  @Resource
  private ReportExportTaskMapper taskMapper;

  private final ScheduledExecutorService executor;
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  public ReportLazyCleaner() {
    executor = Executors.newScheduledThreadPool(1);
  }

  @PreDestroy
  public void shutdown() {
    executor.shutdown();
  }

  public void delayClean(int minutes, Long taskId, String filePath) {
    executor.schedule(new CleanTask(taskId, filePath), minutes, TimeUnit.MINUTES);
  }

  class CleanTask implements Runnable {

    private final Long taskId;
    private final String filePath;

    CleanTask(Long taskId, String filePath) {
      this.taskId = taskId;
      this.filePath = filePath;
    }

    @Override
    public void run() {
      if (StringUtils.isEmpty(filePath)) {
        return;
      }
      File file = new File(filePath);
      if (!file.exists()) {
        taskMapper.updateTaskStatus(taskId, TaskStatusEnum.EXPIRED.getStatus(), "");
      } else {
        if (file.delete()) {
          log.info("文件删除成功 taskId:{} file:{}", taskId, filePath);
          taskMapper.updateTaskStatus(taskId, TaskStatusEnum.EXPIRED.getStatus(), "");
        } else {
          executor.schedule(new CleanTask(taskId, filePath), 10, TimeUnit.SECONDS);
        }
      }
    }
  }
}
