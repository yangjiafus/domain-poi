package com.ctspcl.poi.validator;


import com.ctspcl.poi.imports.PoiImportContent;
import org.springframework.util.CollectionUtils;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/11
 **/
public class DefaultExcelValidator implements CellValidator {

    private RowValidator rowValidator;

    public DefaultExcelValidator(RowValidator rowValidator) {
        this.rowValidator = rowValidator;
    }

    public DefaultExcelValidator() {
        rowValidator = new RowValidator(){};
    }

    public void startValid() {
        if (!CollectionUtils.isEmpty(PoiImportContent.getTable().getData())){
            PoiImportContent.getTable().getData().stream().forEach(row -> {
                rowValidator.valid(row,PoiImportContent.getRowRule());
                PoiImportContent.getHeader().getHeaderNodes().stream().forEach(headerNode -> {
                    this.valid(row.getCell(headerNode.getCellNode().getIndex()),headerNode.getCellNode().getRule());
                });
            });
        }
    }

}
