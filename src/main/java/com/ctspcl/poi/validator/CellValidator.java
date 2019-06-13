package com.ctspcl.poi.validator;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import com.ctspcl.poi.rule.Rule;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/11
 **/
public interface CellValidator extends ValidateEnable<Cell>{

    /**
     *  验证单元格数据
     * @param cell
     * @param rule
     * */
    @Override
    default void valid(Cell cell,Rule rule){
        if (rule != null){
            if (cell != null){
                if (!rule.isMatchRule(cell)){
                    StringBuffer stb = new StringBuffer();
                    stb.append("第 ").append(cell.getRowIndex() + 1).append(" 行 ").append(cell.getColumnIndex() + 1).append(" 列数据错误");
                    throw new BizException(ErrorCode.SERVER_ERROR.getCode(),stb.toString());
                }
            }
        }
    }

}
