package com.ctspcl.poi;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/2/19
 **/
public class WorkbookFactory {


    public static Workbook create(InputStream inputStream) throws Exception{
        try {
            return org.apache.poi.ss.usermodel.WorkbookFactory.create(inputStream);
        }catch (Exception e){
            throw new BizException(ErrorCode.SERVER_ERROR.getCode(),"文件类型错误");
        }
    }
}
