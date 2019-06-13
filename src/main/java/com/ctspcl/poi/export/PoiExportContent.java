package com.ctspcl.poi.export;

import com.ctspcl.poi.imports.TableHeader;

import java.util.List;

/**
 * @author JiaFu.yang
 * @description TODO 目前仅支持小数据了的导出，大数据量时，要考虑ThreadLocal内存溢出的情况
 * @date 2019/6/10
 **/
public class PoiExportContent {

    private static final ThreadLocal<List<EnableExport>> exportData = new ThreadLocal<>();
    private static final ThreadLocal<TableHeader> header = new ThreadLocal<>();
    /**
     * TODO 多表单导出
     * 表单名称
     */
    private static final ThreadLocal<String> sheetName = new ThreadLocal<>();
    private static final ThreadLocal<String> exportFileName = new ThreadLocal<>();
    private static final ThreadLocal<String> fileType = new ThreadLocal<>();



    public static void setExportData(List<EnableExport> enableExports){
        exportData.set(enableExports);
    }

    public static void setHeader(String ... headerNames){
        if (headerNames != null && headerNames.length > 0){
            header.set(new TableHeader(headerNames));
        }
    }

    public static void setFileType(String type){
        fileType.set(type);
    }

    public static String getFileType() {
        return fileType.get();
    }

    public static void setSheetName(String sn){
        sheetName.set(sn);
    }

    public static void setExportFileName(String efn){
        exportFileName.set(efn);
    }

    public static String getSheetName() {
        return sheetName.get();
    }

    public static String getExportFileName() {
        return exportFileName.get();
    }

    public static TableHeader getHeader(){
        return header.get();
    }

    public static List<EnableExport> getExprotData(){
        return exportData.get();
    }

    public static void remove(){
        exportData.remove();
        header.remove();
        sheetName.remove();
        exportFileName.remove();
        fileType.remove();
    }
}
