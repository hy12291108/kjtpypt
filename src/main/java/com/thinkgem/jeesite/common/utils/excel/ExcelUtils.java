package com.thinkgem.jeesite.common.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by 白子涵
 * 替换Excel模板工具类
 */
public class ExcelUtils {

    /**
     * 替换Excel模板文件内容
     * @param item 文档数据
     * @param sourceFilePath Excel模板文件路径
     * @param targetFilePath Excel生成文件路径
     */
    public static boolean replaceModel(Map item, String sourceFilePath, String targetFilePath) {
        boolean bool = true;
        try {
            POIFSFileSystem fs  =new POIFSFileSystem(new FileInputStream(sourceFilePath));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                HSSFRow row = (HSSFRow) rows.next();
                if(row!=null) {
                    int num = row.getLastCellNum();
                    for(int i=0;i<num;i++) {
                        HSSFCell cell=  row.getCell(i);
                        if(cell!=null) {
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        }
                        if(cell==null || cell.getStringCellValue()==null) {
                            continue;
                        }
                        //获取该单元格的值
                        String value= cell.getStringCellValue();
                        if(checkText(value)){   //断表中中对应单元格的值是否包含$
                            Set<String> keySet = item.keySet();
                            Iterator<String> it = keySet.iterator();
                            while (it.hasNext()) {
                                String text = it.next();
                                if(value.equalsIgnoreCase(text)) {
                                    cell.setCellValue((String)item.get(text));
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            //判断本地是否存在当前文件夹，如果没有则创建对应文件夹
            File dir = new File(targetFilePath.substring(0,targetFilePath.lastIndexOf("/")));
            if (!dir.isDirectory())dir.mkdirs();

            // 输出文件
            FileOutputStream fileOut = new FileOutputStream(targetFilePath);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }


    /**
     * 判断文本中时候包含$
     * @param text 文本
     * @return 包含返回true,不包含返回false
     */
    public static boolean checkText(String text){
        boolean check  =  false;
        if(text.indexOf("$")!= -1){
            check = true;
        }
        return check;
    }

}