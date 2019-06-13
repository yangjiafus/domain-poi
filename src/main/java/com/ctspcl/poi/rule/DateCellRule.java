package com.ctspcl.poi.rule;

import com.ctspcl.poi.DateFormatEnable;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/2/22
 **/
@Getter
public class DateCellRule extends CellRule implements DateFormatEnable {

    private DateTimeFormatter df;

    private Class dateClass;

    public DateCellRule(DateTimeFormatter df, Class dateClass) {
        this.df = df;
        this.dateClass = dateClass;
    }

    @Override
    public boolean isMatchRule(Cell cell) {
        return HSSFDateUtil.isCellDateFormatted(cell);
    }


    @Override
    public LocalDateTime format(String timeStr){
        return LocalDateTime.parse(timeStr,df);
    }
}
