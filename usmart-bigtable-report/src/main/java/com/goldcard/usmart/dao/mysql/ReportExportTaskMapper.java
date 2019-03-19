package com.goldcard.usmart.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcard.usmart.domain.report.ReportExportTask;
import com.goldcard.usmart.domain.report.ReportExportTasksReq;

public interface ReportExportTaskMapper {

  ReportExportTask selectByPrimaryKey(Long id);

  /**
   * 插入新的任务
   *
   * @param record 待插入的任务对象
   * @return 受影响的行数，record 的 id 将更新为自动生成的主键
   */
  Long insert(ReportExportTask record);

  /**
   * 更新任务状态
   *
   * @param id 任务ID
   * @param taskStatus 新的任务状态
   * @param fileName 导出文件路径
   * @return 受影响的行数
   */
  int updateTaskStatus(@Param("id") Long id, @Param("taskStatus") String taskStatus,
      @Param("fileName") String fileName);

  /**
   * 查询相应任务状态的任务数量
   *
   * @param taskStatus 任务状态
   * @param tenantId 租户ID
   * @return 相应任务状态的任务数量
   */
  long countByTaskStatus(@Param("taskStatus") String taskStatus,
      @Param("tenantId") String tenantId, @Param("reportType") String reportType);

  /**
   * 返回符合查询条件的任务数量
   *
   * @param req 查询条件
   * @return 任务数量
   */
  long countByReq(ReportExportTasksReq req);

  /**
   * 返回符合查询条件的任务分页列表
   *
   * @param req 查询条件
   * @return 符合查询条件的结果集
   */
  List<ReportExportTask> findPageByReq(ReportExportTasksReq req);

  /**
   * 根据任务 id 与数据权限获取指定任务
   *
   * @param id 任务 id
   * @param orgId 数据权限
   * @param tenantId 租户 id
   * @return 指定任务
   */
  ReportExportTask findCompleteTask(@Param("id") Long id, @Param("orgId") String orgId,
      @Param("tenantId") String tenantId);
}