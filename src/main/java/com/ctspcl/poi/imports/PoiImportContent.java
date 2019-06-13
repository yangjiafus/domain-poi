package com.ctspcl.poi.imports;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import com.ctspcl.poi.Table;
import com.ctspcl.poi.rule.RowRule;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class PoiImportContent {

    private static final ThreadLocal<Table> importData = new ThreadLocal<>();
    private static final ThreadLocal<Workbook> workbook = new ThreadLocal<>();
    /**映射模型的字段属性*/
    private static final ThreadLocal<TableHeader> header = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> importDataHasHeader = new ThreadLocal<>();
    private static final ThreadLocal<RowRule> rowRule = new ThreadLocal<>();
    public static final int MAX_ROW = 50000;

    public static void registryRowRule (RowRule rowR){
        rowRule.set(rowR);
    }

    public static RowRule getRowRule() {
        return rowRule.get();
    }

    public static Boolean hasHeader(){
        return importDataHasHeader.get();
    }

    public static void setImportDataHasHeader(boolean hasHeader){
        importDataHasHeader.set(hasHeader);
    }

    public static TableHeader getHeader(){
        return header.get();
    }

    public static void setHeader(TableHeader tableHeader){
        if (!CollectionUtils.isEmpty(tableHeader.getHeaderNodes())){
            header.set(tableHeader);
        }
    }

    public static Workbook getWorkbook(){
        return workbook.get();
    }

    public static void setWorkbook(Workbook wk){
        workbook.set(wk);
    }

    public static Table getTable(){
        if (importData.get() == null){
            throw new BizException(ErrorCode.SERVER_ERROR.getCode(),"请先导入Excel表格");
        }
        return importData.get();
    }

    public static void setTable(Table t){
        importData.set(t);
    }

    public static void remove(){
        importData.remove();
        workbook.remove();
        header.remove();
        importDataHasHeader.remove();
        rowRule.remove();
    }

    public static List<Sheet> getSheets(){
        if (getWorkbook() != null){
            List<Sheet> sheetList = Lists.newArrayList();
            Iterator<Sheet> iterator = getWorkbook().sheetIterator();
            while (iterator.hasNext()){
                sheetList.add(iterator.next());
            }
            return sheetList;
        }
        return null;
    }

    public static Sheet getSheetByIndex(int index){
        if (!CollectionUtils.isEmpty(getSheets())){
            return getSheets().get(index);
        }
        return null;
    }

    public static int getStartRowIndex(){
        int start = 0;
        if (hasHeader()){
            start++;
        }
        return start;
    }
}
