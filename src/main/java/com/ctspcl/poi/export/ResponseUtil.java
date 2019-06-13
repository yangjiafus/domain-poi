package com.ctspcl.poi.export;

import com.ctspcl.common.exception.BizException;
import com.ctspcl.common.exception.ErrorCode;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class ResponseUtil {

    public static void responseStream(Workbook workbook, HttpServletResponse response){
        try {
            //TODO 编码问题
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(PoiExportContent.getExportFileName(),"UTF-8") + PoiExportContent.getFileType());
            OutputStream outputStream = response.getOutputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            workbook.write(buffer);
            byte[] bytesArr = buffer.toByteArray();
            outputStream.write(bytesArr);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            throw new BizException(ErrorCode.SERVER_ERROR.getCode(),"导出异常");
        }
    }

}
