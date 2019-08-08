package com.ctspcl.poi.rule;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import com.ctspcl.poi.DateFormatEnable;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/2/22
 **/
@Getter
public class DateCellRule extends CellRule implements DateFormatEnable {

    private DateTimeFormatter df;

    private Class dateClass;

    protected final static String LOCAL_DATE_TIME = "java.time.LocalDateTime";
    protected final static String LOCAL_DATE = "java.time.LocalDate";

    public DateCellRule(DateTimeFormatter df, Class dateClass) {
        this.df = df;
        this.dateClass = dateClass;
    }

    @Override
    public boolean isMatchRule(Cell cell) {
        String beforeTime = cell.getStringCellValue();
        try {
            if (parseTemporal(beforeTime) != null){
                return true;
            }
        }catch (Exception e){
            if (e instanceof  BizException){
                throw e;
            }
        }
        try {
            if (parseDate(beforeTime) != null){
                return true;
            }
        }catch (Exception e){
        }

        cell.setCellType(CellType.NUMERIC);
        boolean isDate = HSSFDateUtil.isCellDateFormatted(cell);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(beforeTime);
        return isDate;
    }

    private Date parseDate(String timeStr){
        return HSSFDateUtil.getJavaDate(Double.parseDouble(timeStr));
    }

    private Temporal parseTemporal(String timeStr){
        if (this.dateClass.getName().equals("java.time.LocalDateTime")) {
            return LocalDateTime.parse(timeStr, this.df);
        }
        if (this.dateClass.getName().equals("java.time.LocalDateTime")) {
            return LocalDateTime.parse(timeStr, this.df);
        }
        throw new BizException(ErrorCode.SERVER_ERROR.getCode(),"POI 插件未指定时间类型");
    }

    @Override
    public Temporal format(String timeStr,Cell cell){

        try {
            Temporal temporal = parseTemporal(timeStr);
            if (temporal != null){
                return temporal;
            }
        } catch (Exception e) {
            if (e instanceof  BizException){
                throw e;
            }
        }
        Date time = parseDate(timeStr);
        if (time != null){
            if (this.dateClass.getName().equals("java.time.LocalDateTime")) {
                return Instant.ofEpochMilli(time.getTime()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
            } else if (this.dateClass.getName().equals("java.time.LocalDate")) {
                return Instant.ofEpochMilli(time.getTime()).atZone(ZoneOffset.ofHours(8)).toLocalDate();
            }
        }
        throw new BizException(ErrorCode.SERVER_ERROR.getCode(), "时间格式或表格类型不正确");
    }
}
