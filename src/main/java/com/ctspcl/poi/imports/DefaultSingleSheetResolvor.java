package com.ctspcl.poi.imports;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import com.ctspcl.poi.Table;
import com.ctspcl.poi.rule.NotBlankRowRule;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;
import java.util.List;

import static com.ctspcl.poi.imports.PoiImportContent.MAX_ROW;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class DefaultSingleSheetResolvor implements ResolveEnable{


    @Override
    public void resolve() {
        Sheet firstSheet = getFirstSheet();
        Iterator<Row> iterator = firstSheet.rowIterator();
        int skip = PoiImportContent.getStartRowIndex();
        int start = 0;
        List<Row> validRows = Lists.newArrayList();
        Row r = null;
        Iterator<Cell> ci;
        Cell c;
        NotBlankRowRule notBlankRowRule = new NotBlankRowRule();
        while (iterator.hasNext()){
            if (start < skip){
                iterator.next();
                start++;
                continue;
            }
            r = iterator.next();
            if (notBlankRowRule.isMatchRule(r)){
                ci = r.cellIterator();
                while (ci.hasNext()){
                    c = ci.next();
                    c.setCellType(CellType.STRING);
                }
                validRows.add(r);
            }
        }
        PoiImportContent.setTable(new Table(validRows));
    }

    private Sheet getFirstSheet() {
        Sheet sheet = PoiImportContent.getSheetByIndex(0);
        if (sheet == null) {
            throw new BizException(ErrorCode.SERVER_ERROR.getCode(),"Excel数据不能为空");
        }
        if (MAX_ROW < sheet.getLastRowNum()){
            throw new BizException(ErrorCode.SERVER_ERROR.getCode(),"Excel最多只能批量导入50000条数据!!");
        }
        return sheet;
    }
}
