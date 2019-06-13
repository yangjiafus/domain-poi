package com.ctspcl.poi.validator;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import com.ctspcl.poi.rule.Rule;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/11
 **/
public interface RowValidator extends ValidateEnable<Row>{
    /**
     *  验证单元格数据
     * @param row
     * @param rule
     * */
    @Override
    default void valid(Row row, Rule rule) {
        if (rule != null) {
            if (row != null){
                if (!rule.isMatchRule(row)){
                    StringBuffer stb = new StringBuffer();
                    stb.append("第 ").append(row.getRowNum() + 1).append(" 行 ").append(" 数据错误");
                    throw new BizException(ErrorCode.SERVER_ERROR.getCode(),stb.toString());
                }
            }
        }
    }
}
