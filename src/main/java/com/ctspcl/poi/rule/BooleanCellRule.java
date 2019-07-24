package com.ctspcl.poi.rule;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/2/22
 **/
public class BooleanCellRule extends CellRule{

    @Override
    public boolean isMatchRule(Cell cell) {
        String cellValue = cell.getStringCellValue();
        return (String.valueOf(Boolean.FALSE).equalsIgnoreCase(cellValue) ||
                String.valueOf(Boolean.TRUE).equalsIgnoreCase(cellValue));
    }


}
