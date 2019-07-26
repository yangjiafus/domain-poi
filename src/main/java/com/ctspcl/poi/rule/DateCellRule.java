package com.ctspcl.poi.rule;

import com.ctspcl.poi.DateFormatEnable;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

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
        cell.setCellType(CellType.NUMERIC);
        boolean isDate = HSSFDateUtil.isCellDateFormatted(cell);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(beforeTime);
        return isDate;
    }


    @Override
    public Temporal format(String timeStr){

        if (dateClass.getName().equals(LOCAL_DATE_TIME)) {
            return LocalDateTime.parse(timeStr,df);
        }

        if (dateClass.getName().equals(LOCAL_DATE)) {
            return LocalDate.parse(timeStr,df);
        }

        return null;
    }
}
