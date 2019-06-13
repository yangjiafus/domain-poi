package com.ctspcl.poi.export;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author JiaFu.yang
 * @description 写入一行字符串
 * @date 2019/6/10
 **/
public class DefaultWriteRow implements CreateRowEnable{


    @Override
    public <T> void createRow(Sheet sheet, int rowNum, List<T> cellContents) {
        HSSFSheet hssfSheet = (HSSFSheet)sheet;
        Row row = hssfSheet.createRow(rowNum);
        setRow(row,cellContents);
    }

    @Override
    public <T> void setRow(Row row,List<T> cellContents) {
        Cell cell;
        int index = 0;
        for (Object cellContent : cellContents){
            cell = row.createCell(index);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(String.valueOf(cellContent));
            index++;
        }
    }

}
