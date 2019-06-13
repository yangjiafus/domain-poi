package com.ctspcl.poi.rule;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.StringUtils;

import java.util.Iterator;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class NotBlankRowRule extends RowRule{


    @Override
    public boolean isMatchRule(Row row) {
        return isNotBlankRow(row);
    }

    private boolean isNotBlankRow(Row r){
        Iterator<Cell> iterator = r.cellIterator();
        boolean isValid = false;
        Cell cell;
        while (iterator.hasNext()){
            cell = iterator.next();
            if (cell != null){
                if (!cell.getCellTypeEnum().equals(CellType.BLANK)){
                    if (cell.getCellTypeEnum().equals(CellType.STRING)){
                        if (StringUtils.isEmpty(cell.getStringCellValue())){
                            continue;
                        }
                    }
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }

}
