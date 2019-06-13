package com.ctspcl.poi.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public interface Export {

    /**
     *  创建 HSSFWorkbook 并写入数据
     * @return
     * @see HSSFWorkbook
     * */
    Workbook exportResolve();

}
