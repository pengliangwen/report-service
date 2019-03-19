package com.goldcard.usmart.service.impl;

import java.io.IOException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldcard.usmart.dao.ch.CollectionRecordMapper;
import com.goldcard.usmart.dao.mysql.MeterMapper;
import com.goldcard.usmart.domain.report.query.CollectionRecordQueryDTO;
import com.goldcard.usmart.domain.report.result.CollectionRecordResultDTO;
import com.goldcard.usmart.enums.ReportTypeEnum;
import com.goldcard.usmart.service.ReportBuilderTemplate;
import com.goldcard.usmart.utils.CursorReaderBuilder;
import com.goldcard.usmart.utils.DateFilterUtil;

/**
 * @author 2299
 * @version 1.0 2019-01-28
 */
@Component("collectionRecordReport")
public class CollectionRecordReport implements
        ReportBuilderTemplate<CollectionRecordQueryDTO, CollectionRecordResultDTO> {

  private static Logger logger = LoggerFactory.getLogger(CollectionRecordReport.class);
  private static final String QUERY_MAPPER_ID = "com.goldcard.usmart.dao.ch.CollectionRecordMapper.findByQueryDTO";

  @Resource
  private CollectionRecordMapper mapper;
  @Autowired
  CursorReaderBuilder readerBuilder;

  private final ObjectMapper objectMapper;
  private final ThreadLocal<CollectionRecordQueryDTO> dtoThreadLocal;
  private final ThreadLocal<Map<String, CellStyle>> cellStyleMap;
  @Autowired
  MeterMapper meterMapper;

  @Autowired
  public CollectionRecordReport(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    dtoThreadLocal = new ThreadLocal<>();
    cellStyleMap = ThreadLocal.withInitial(HashMap::new);
  }

  @Override
  public MyBatisCursorItemReader<CollectionRecordResultDTO> getRead() {
    return readerBuilder.buildReader(dtoThreadLocal.get(), QUERY_MAPPER_ID);
  }
  @Override
  public int buildReportHead(Sheet sh) {
    CollectionRecordQueryDTO queryDTO = dtoThreadLocal.get();

    CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 12);   // 起始行号，终止行号， 起始列号，终止列号
    sh.addMergedRegion(rangeAddress);

    Row reportName = sh.createRow(0);
    CellStyle cellStyle = sh.getWorkbook().createCellStyle();
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    Cell cell = reportName.createCell(0);
    String head = getReportType().getName();
    if (queryDTO.getCollectionTimeStart() != null) {
      head += "（采集时间：" + queryDTO.getCollectionTimeStart()+ " 至 " + queryDTO.getCollectionTimeEnd() + "）";
    }
    cell.setCellValue(head);
    cell.setCellStyle(cellStyle);

    Row reportHead = sh.createRow(1);
    Cell cellHeadMeterNo = reportHead.createCell(0);
    cellHeadMeterNo.setCellValue("表具编号");
    Cell cellHeadCustomerName = reportHead.createCell(1);
    cellHeadCustomerName.setCellValue("客户名称");
    Cell cellHeadCollectionTime = reportHead.createCell(2);
    cellHeadCollectionTime.setCellValue("采集时间");
    Cell cellHeadDataDate = reportHead.createCell(3);
    cellHeadDataDate.setCellValue("数据时间");
    Cell cellHeadMeterTime = reportHead.createCell(4);
    cellHeadMeterTime.setCellValue("表内时钟");
    Cell cellHeadMeterReading = reportHead.createCell(5);
    cellHeadMeterReading.setCellValue("表读数");
    Cell cellHeadStandardSum = reportHead.createCell(6);
    cellHeadStandardSum.setCellValue("标况累积量");
    Cell cellHeadWorkingSum = reportHead.createCell(7);
    cellHeadWorkingSum.setCellValue("工况累积量");
    Cell cellHeadBalanceQuantity = reportHead.createCell(8);
    cellHeadBalanceQuantity.setCellValue("表内剩余量");
    Cell cellHeadStandardFlow = reportHead.createCell(9);
    cellHeadStandardFlow.setCellValue("标况瞬时流量");
    Cell cellHeadWorkingFlow = reportHead.createCell(10);
    cellHeadWorkingFlow.setCellValue("工况瞬时流量");
    Cell cellHeadTemperature = reportHead.createCell(11);
    cellHeadTemperature.setCellValue("温度");
    Cell cellHeadPressure = reportHead.createCell(12);
    cellHeadPressure.setCellValue("压力");
    return 1;
  }

  @Override
  public void buildDateRow(Sheet sh, int rowIndex, CollectionRecordResultDTO dto) {
    Row dataRow = sh.createRow(rowIndex);
    Cell cellEqNo = dataRow.createCell(0);
    cellEqNo.setCellValue(dto.getEqNo());
    Cell cellCustomerName = dataRow.createCell(1);
    cellCustomerName.setCellValue(dto.getCustomerName());
    CellStyle cellStyle = getCellStyle(sh);
    Cell cellCreateTime = dataRow.createCell(2);
    cellCreateTime.setCellValue(dto.getCreateTime());
    cellCreateTime.setCellStyle(cellStyle);
    Cell cellDataDate = dataRow.createCell(3);
    cellDataDate.setCellValue(dto.getDataDate());
    cellDataDate.setCellStyle(cellStyle);
    Cell cellMeterTime = dataRow.createCell(4);
    cellMeterTime.setCellValue(dto.getMeterTime());
    cellMeterTime.setCellStyle(cellStyle);
    Cell cellMeterReading = dataRow.createCell(5);
    cellMeterReading.setCellValue(dto.getStandardSum());
    Cell cellStandardSum = dataRow.createCell(6);
    cellStandardSum.setCellValue(dto.getStandardSum());
    Cell cellWorkingSum = dataRow.createCell(7);
    cellWorkingSum.setCellValue(dto.getWorkingSum());
    Cell cellBalanceQuantity = dataRow.createCell(8);
    cellBalanceQuantity.setCellValue(dto.getBalanceQuantity());
    Cell cellStandardFlow = dataRow.createCell(9);
    cellStandardFlow.setCellValue(dto.getStandardFlow());
    Cell cellWorkingFlow = dataRow.createCell(10);
    cellWorkingFlow.setCellValue(dto.getWorkingFlow());
    Cell cellTemperature = dataRow.createCell(11);
    cellTemperature.setCellValue(dto.getTemperature());
    Cell cellPressure = dataRow.createCell(12);
    cellPressure.setCellValue(dto.getPressure());
  }

  @Override
  public void buildReportFoot(Sheet sh, int rowIndex) {
    dtoThreadLocal.remove();
    cellStyleMap.remove();
  }

  @Override
  public CollectionRecordQueryDTO convertAndVerify(String json) {
    CollectionRecordQueryDTO dto;
    try {
      dto = objectMapper.readValue(json, CollectionRecordQueryDTO.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("任务查询格式异常");
    }
    if (!StringUtils.isEmpty(dto.getCustomerName())) {
      dto.setCustomerName(dto.getCustomerName() + "%");
    }
    if (!StringUtils.isEmpty(dto.getOrgId())) {
      dto.setOrgId(dto.getOrgId() + "%");
    }
    // 起始日期要小于结束日期
    if(DateFilterUtil.parserYMD(dto.getCollectionTimeStart()).after(DateFilterUtil.parserYMD(dto.getCollectionTimeEnd()))) {
      throw new IllegalArgumentException("起始日期必须小于结束日期!");
    }
    // 查询开始时间大于系统当前时间，直接返回无数据
    if (DateFilterUtil.parserYMD(dto.getCollectionTimeStart()).after(new java.util.Date())) {
      throw new IllegalArgumentException("该查询条件下无数据！");
    }
    // 起始温度或结束温度不为空时， 填写非数字则报错
    if ((!StringUtils.isEmpty(dto.getTemperatureStart()) && !isNumeric(dto.getTemperatureStart()))
            || (!StringUtils.isEmpty(dto.getTemperatureEnd()) && !isNumeric(dto.getTemperatureEnd()))) {
      throw  new IllegalArgumentException("温度范围条件中，请输入数字，而非字母或其它类型值！");
    }
    // 起始温度要小于结束温度，且必须为数字类型
    if (!StringUtils.isEmpty(dto.getTemperatureStart()) && !StringUtils.isEmpty(dto.getTemperatureEnd())) {
      if (!isNumeric(dto.getTemperatureStart()) || !isNumeric(dto.getTemperatureEnd())) {
        throw new IllegalArgumentException("温度范围条件中，请输入数字，而非字母或其它类型值！");
      }
      if (Double.parseDouble(dto.getTemperatureStart()) > Double.parseDouble(dto.getTemperatureEnd())) {
        throw new IllegalArgumentException("温度初值必须小于温度终值!");
      }
    }
    // 压力初值或压力终值不为空时， 填写非数字则报错
    if ((!StringUtils.isEmpty(dto.getPressureStart()) && !isNumeric(dto.getPressureStart()))
            || (!StringUtils.isEmpty(dto.getPressureEnd()) && !isNumeric(dto.getPressureEnd()))) {
      throw  new IllegalArgumentException("压力范围条件中，请输入数字，而非字母或其它类型值！");
    }
    // 起始压力必须小于终止压力
    if (!StringUtils.isEmpty(dto.getPressureStart()) && !StringUtils.isEmpty(dto.getPressureEnd())) {
      if (!isNumeric(dto.getPressureStart()) || !isNumeric(dto.getPressureEnd())) {
        throw new IllegalArgumentException("压力范围条件中，请输入数字，而非字母或其它类型值！");
      }
      if (Double.parseDouble(dto.getPressureStart()) > Double.parseDouble(dto.getPressureEnd())) {
        throw new IllegalArgumentException("压力初值必须小于压力终值!");
      }
    }
    //标况瞬时流量初值或标况瞬时流量终值不为空时， 填写非数字则报错
    if ((!StringUtils.isEmpty(dto.getStandardFlowStart()) && !isNumeric(dto.getStandardFlowStart()))
            || (!StringUtils.isEmpty(dto.getStandardFlowEnd()) && !isNumeric(dto.getStandardFlowEnd()))) {
      throw  new IllegalArgumentException("标况瞬时流量范围条件中，请输入数字，而非字母或其它类型值！");
    }
    // 标况瞬时流量初值必须小于标况瞬时流量终值
    if (!StringUtils.isEmpty(dto.getStandardFlowStart()) && !StringUtils.isEmpty(dto.getStandardFlowEnd())) {
      if (!isNumeric(dto.getStandardFlowStart()) || !isNumeric(dto.getStandardFlowEnd())) {
        throw new IllegalArgumentException("标况瞬时流量范围条件中，请输入数字，而非字母或其它类型值！");
      }
      if (Double.parseDouble(dto.getStandardFlowStart()) > Double.parseDouble(dto.getStandardFlowEnd())) {
        throw new IllegalArgumentException("标况瞬时流量初值必须小于标况瞬时流量终值!");
      }
    }
    dtoThreadLocal.set(dto);
    return dto;
  }

  @Override
  public long count() {
    CollectionRecordQueryDTO queryDTO = dtoThreadLocal.get();
    long residentMeterCount = meterMapper.countWithResidentMeter(queryDTO);
    long businessMeterCount = meterMapper.countWithBusinessMeter(queryDTO);
    long dTUCount = meterMapper.countWithDTU(queryDTO);
    long otherCount = meterMapper.countWithOther(queryDTO);
    long meterCount = residentMeterCount + businessMeterCount * 144 + dTUCount * 24 + otherCount*24;
    int diffDay = 0;
    if (queryDTO.getCollectionTimeStart() != null && queryDTO.getCollectionTimeEnd() != null) {
      diffDay = DateFilterUtil.getDayNumber(DateFilterUtil.parserYMD(queryDTO.getCollectionTimeStart()), DateFilterUtil.parserYMD(queryDTO.getCollectionTimeEnd()));
    }
    return diffDay == 0 ? meterCount : meterCount * diffDay;
  }


  @Override
  public ReportTypeEnum getReportType() {
    return ReportTypeEnum.COLLECTION_RECORD;
  }

  @Override
  public CollectionRecordQueryDTO getQueryDTO() {
    return dtoThreadLocal.get();
  }

  @Override
  public void setQueryDTO(CollectionRecordQueryDTO queryDTO) {
    dtoThreadLocal.set(queryDTO);
  }

  private int diffDay(Date a, Date b) {
    return (int) Math.abs(ChronoUnit.DAYS.between(a.toLocalDate(), b.toLocalDate())) + 1;
  }

  private CellStyle getCellStyle(Sheet sheet) {
    CellStyle cellStyle = cellStyleMap.get().get("dataFormat");
    if (cellStyle == null) {
      cellStyle = sheet.getWorkbook().createCellStyle();
      CreationHelper createHelper = sheet.getWorkbook().getCreationHelper();
      cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
      cellStyleMap.get().put("dataFormat", cellStyle);
    }
    return cellStyle;
  }

// 判断字符串是否为数字
  private boolean isNumeric(String str){
//    Pattern pattern = Pattern.compile("[0-9]*");
    Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");  // 包含正负情况^[-+]?[0-9]+(\\.[0-9]+)?$     ^[0-9]+([.]{1}[0-9]+){0,1}$
    Matcher isNum = pattern.matcher(str);
    if( !isNum.matches() ){
      return false;
    }
    return true;
  }
}
