package com.o98k.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 周大伟
 * @Date: 2019/2/14 10:41
 */
public class PoiExcelUtil {

    public static List<List<String>> readInExcel(String filePath) throws Exception {
        //1.读取Excel文档对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
        //2.获取要解析的表格（第一个表格）
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //获得最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        //
        List<List<String>> list = new LinkedList<>();
        for (int i = 0; i <= lastRowNum; i++) {//遍历每一行
            //3.获得要解析的行
            XSSFRow row = sheet.getRow(i);
            //4.获得每个单元格中的内容（String）
            if(!StringUtils.isEmpty(row)){
                int len = row.getLastCellNum();
                //
                List<String> strs = new ArrayList<>();
                for (int j = 0; j < len; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (!StringUtils.isEmpty(cell)) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        strs.add(cell.getStringCellValue());
                    }else {
                        strs.add("");
                    }
                }
                //
                list.add(strs);
            }
        }
        return list;
    }


}
