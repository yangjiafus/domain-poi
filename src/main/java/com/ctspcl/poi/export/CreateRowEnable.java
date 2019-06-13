package com.ctspcl.poi.export;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author JiaFu.yang
 * @description TODO 常见文本类型导出
 * @date 2019/6/10
 **/
public interface CreateRowEnable {

    /**
     * 创建行，并初始化行。默认用  HSSFSheet 创建行
     * @param cellContents
     * @param rowNum
     * @param sheet
     * @see HSSFSheet
     * */
    <T> void createRow(Sheet sheet,int rowNum,List<T> cellContents);

    /**
     * 初始化行昂书
     * @param row
     * @param cellContents
     * */
    <T> void setRow(Row row, List<T> cellContents);
}
