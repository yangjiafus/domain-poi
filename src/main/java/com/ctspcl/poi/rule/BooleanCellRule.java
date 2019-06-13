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
        return cell.getCellTypeEnum().equals(CellType.BOOLEAN);
    }
}
