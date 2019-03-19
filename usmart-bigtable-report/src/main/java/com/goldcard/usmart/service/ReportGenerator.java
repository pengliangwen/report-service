package com.goldcard.usmart.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goldcard.usmart.dao.ch.CollectionRecordMapper;
import com.goldcard.usmart.dao.mysql.ReportExportTaskMapper;
import com.goldcard.usmart.domain.report.TaskStatusEnum;
import com.goldcard.usmart.domain.report.query.CollectionRecordQueryDTO;
import com.goldcard.usmart.domain.report.query.QueryDTO;
import com.goldcard.usmart.domain.report.result.CollectionRecordResultDTO;
import com.goldcard.usmart.domain.report.result.ResultDTO;

/**
 * @author 2299
 * @version 1.0 2018-11-06
 */
@Component
public class ReportGenerator {

  private final ReportLazyCleaner cleaner;
  private final Logger log = LoggerFactory.getLogger(ReportGenerator.class);

  @Autowired
  private ReportExportTaskMapper taskMapper;

  @Value("${report.file-path}")
  private String filePath;
  @Value("${report.clean-after-build}")
  private Integer cleanAfterBuild;
  @Autowired
  CollectionRecordMapper collectionRecordMapper;

  @Autowired
  public ReportGenerator(
      ReportLazyCleaner cleaner) {
    this.cleaner = cleaner;
  }

  @Async("threadPoolTaskExecutor")
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void generate(Long taskId, QueryDTO queryDTO,
      ReportBuilderTemplate<QueryDTO, ResultDTO> template) {
    log.info("报表生成任务 TASK[{}] 开始执行", taskId);
    long startTime = System.nanoTime();

    template.setQueryDTO(queryDTO);
    File dir = new File(filePath);
    if (!dir.exists()) {
      boolean mkdir = dir.mkdir();
      if (!mkdir) {
        taskMapper.updateTaskStatus(taskId, TaskStatusEnum.MISTAKE.getStatus(), null);
        log.error("创建导出报表文件夹失败 taskId:{} getReportType:{} getQueryDTO:{}", taskId,
            template.getReportType(), template.getQueryDTO());
        return;
      }
    }
    MyBatisCursorItemReader<ResultDTO> reader = template.getRead();
    SXSSFWorkbook wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
    Sheet sh = wb.createSheet();
    int rowIndex = template.buildReportHead(sh);
    String reportName = template.getReportType()
        .buildFileName(taskId, template.getQueryDTO().getTenantId());
    File reportFile = new File(dir, reportName);

    try (OutputStream outputStream = new FileOutputStream(reportFile)) {
        reader.open(new ExecutionContext());
        ResultDTO dto;
        while ((dto = reader.read()) != null) {
	        if (rowIndex >= SpreadsheetVersion.EXCEL2007.getLastRowIndex()) {
	          sh = wb.createSheet();
	          rowIndex = template.buildReportHead(sh);
	        }
	        rowIndex++;
	        template.buildDateRow(sh, rowIndex, dto);
        }
      template.buildReportFoot(sh, rowIndex + 1);
      wb.write(outputStream);
      taskMapper.updateTaskStatus(taskId, TaskStatusEnum.COMPLETE.getStatus(), reportName);
      if (cleanAfterBuild >= 0) {
        cleaner.delayClean(cleanAfterBuild, taskId, reportFile.getPath());
      }
      log.info("报表生成任务 TASK[{}] 完成耗时 {}ms", taskId, (System.nanoTime() - startTime) / 1000000);
    } catch (Exception e) {
      taskMapper.updateTaskStatus(taskId, TaskStatusEnum.MISTAKE.getStatus(), null);
      log.error("导出报表失败 queryDTO:{} taskId:{} Exception:{}", template.getQueryDTO(), taskId,
          e.toString());
      if (reportFile.exists()) {
        boolean delete = reportFile.delete();
        if (!delete) {
          log.error("清除报表失败 filePath:{}", reportFile.getPath());
        }
      }
    } finally {
    	 reader.close();
         wb.dispose();
    }
  }
  
  @Async("threadPoolTaskExecutor")
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  public void generate1(Long taskId, QueryDTO queryDTO,
      ReportBuilderTemplate<QueryDTO, ResultDTO> template) {
    log.info("报表生成任务 TASK[{}] 开始执行", taskId);
    long startTime = System.nanoTime();

    template.setQueryDTO(queryDTO);
    File dir = new File(filePath);
    if (!dir.exists()) {
      boolean mkdir = dir.mkdir();
      if (!mkdir) {
        taskMapper.updateTaskStatus(taskId, TaskStatusEnum.MISTAKE.getStatus(), null);
        log.error("创建导出报表文件夹失败 taskId:{} getReportType:{} getQueryDTO:{}", taskId,
            template.getReportType(), template.getQueryDTO());
        return;
      }
    }
//    MyBatisCursorItemReader<ResultDTO> reader = template.getRead();
    SXSSFWorkbook wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
    Sheet sh = wb.createSheet();
    int rowIndex = template.buildReportHead(sh);
    String reportName = template.getReportType()
        .buildFileName(taskId, template.getQueryDTO().getTenantId());
    File reportFile = new File(dir, reportName);

    try (OutputStream outputStream = new FileOutputStream(reportFile)) {
    	CollectionRecordQueryDTO dtoquey=(CollectionRecordQueryDTO) queryDTO;
    	long time=System.currentTimeMillis();
    	List<CollectionRecordResultDTO> list=collectionRecordMapper.findByQueryDTO(dtoquey);
    	log.info("查询 clikchouse 采集记录,条数：{},时间：{}",list.size(),System.currentTimeMillis()-time);
        for(CollectionRecordResultDTO dto:list){
	        if (rowIndex >= SpreadsheetVersion.EXCEL2007.getLastRowIndex()) {
	          sh = wb.createSheet();
	          rowIndex = template.buildReportHead(sh);
	        }
	        rowIndex++;
	        template.buildDateRow(sh, rowIndex, dto);
        }
      template.buildReportFoot(sh, rowIndex + 1);
      wb.write(outputStream);
      taskMapper.updateTaskStatus(taskId, TaskStatusEnum.COMPLETE.getStatus(), reportName);
      if (cleanAfterBuild >= 0) {
        cleaner.delayClean(cleanAfterBuild, taskId, reportFile.getPath());
      }
      log.info("报表生成任务 TASK[{}] 完成耗时 {}ms", taskId, (System.nanoTime() - startTime) / 1000000);
    } catch (Exception e) {
      taskMapper.updateTaskStatus(taskId, TaskStatusEnum.MISTAKE.getStatus(), null);
      log.error("导出报表失败 queryDTO:{} taskId:{} Exception:{}", template.getQueryDTO(), taskId,
          e.toString());
      if (reportFile.exists()) {
        boolean delete = reportFile.delete();
        if (!delete) {
          log.error("清除报表失败 filePath:{}", reportFile.getPath());
        }
      }
    } finally {
      wb.dispose();
    }
  }
}
