package com.ctspcl.poi.export;

import com.ctspcl.poi.imports.HeaderNode;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author JiaFu.yang
 * @description 导出Excel
 * @date 2019/6/10
 **/
public class DefaultExportExcelSourport implements Export{


    @Override
    public Workbook exportResolve() {

        HSSFSheet sheet = new HSSFWorkbook().createSheet(PoiExportContent.getSheetName());
        final CreateRowEnable createRowEnable = new DefaultWriteRow();
        if (PoiExportContent.getHeader() != null){
            if (!CollectionUtils.isEmpty(PoiExportContent.getHeader().getHeaderNodes())){
                List<HeaderNode> nodes = PoiExportContent.getHeader().getHeaderNodes();
                List<String> headerValues = Lists.newArrayList();
                nodes.stream().forEach(headNode -> headerValues.add(headNode.getHeaderNodeIndex(),headNode.getHeaderNodeName()));
                createRowEnable.createRow(sheet,0,headerValues);
            }
        }

        List<String> stringList = Lists.newArrayList();
        PoiExportContent.getExprotData().stream().forEach(module ->{
            stringList.clear();
            module.getCellValueList().stream().forEach(value -> stringList.add(String.valueOf(value)));
            createRowEnable.createRow(sheet,sheet.getLastRowNum() + 1,stringList);
        });
        return sheet.getWorkbook();
    }


}
