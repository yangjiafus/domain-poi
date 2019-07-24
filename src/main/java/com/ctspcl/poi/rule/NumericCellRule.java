package com.ctspcl.poi.rule;

import org.apache.poi.ss.usermodel.Cell;
import java.util.regex.Pattern;

/**
 * @author JiaFu.yang
 * @description 数字范围规则
 * @date 2019/2/21
 **/
public class NumericCellRule extends CellRule {

    private static final Pattern NUM_PATTERN = Pattern.compile(NumericCellRuleEnum.NUMERIC_REGEX.getRegex());

    @Override
    public boolean isMatchRule(Cell cell) {
        String cellValue = cell.getStringCellValue();
        return NUM_PATTERN.matcher(cellValue).matches();
    }
}
