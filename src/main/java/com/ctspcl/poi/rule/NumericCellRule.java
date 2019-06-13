package com.ctspcl.poi.rule;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author JiaFu.yang
 * @description 数字范围规则
 * @date 2019/2/21
 **/
public class NumericCellRule extends CellRule {

    @Override
    public boolean isMatchRule(Cell cell) {
        return cell.getCellTypeEnum().equals(CellType.NUMERIC);
    }
}
