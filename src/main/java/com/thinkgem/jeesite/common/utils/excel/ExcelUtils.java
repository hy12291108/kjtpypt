package com.thinkgem.jeesite.common.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

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

    // 测试
    public static void main(String[] args) {

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("${name}", "白子涵");
        data.put("${photo}", "头像");
        data.put("${sex}", "男");
        data.put("${email}", "972849203@qq.com");
        data.put("${tpyNation}", "汉族");
        data.put("${tpyLocation}", "陕西省渭南市");
        data.put("${company}", "高新区");
        data.put("${tpyCompany}", "西安市雁塔区高新二路");
        data.put("${tpyPosition}", "JAVA");
        data.put("${tpyTitle}", "初级");
        data.put("${qulification}", "本科");
        data.put("${tpyPolitical}", "团员");
        data.put("${mobile}", "18092448501");
        data.put("${tpyBirthDate}", "1995-05-09");
        data.put("${tpyIdcard}", "610521199505090974");
        data.put("${tpyServiceMode}", "服务形式");
        data.put("${tpyTalentType}", "人才类型");
        data.put("${yjxkdm}", "专业类别");
        data.put("${tpyMajor}", "专业名称");
        data.put("${tpySpecial}", "拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点");
        data.put("${tpyNfwAddress}", "拟服务地点拟服务地点拟服务地点拟服务地点");
        data.put("${tpyNfwContent}", "拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点");
        data.put("${tpyExperience}", "拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点");
        data.put("${tpyJcSituation}", "拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点拟服务地点");

        String path =  "E:/特派员WORD测试/自然人特派员推荐表模板.xls";
        String path2 = "E:/特派员WORD测试/自然人特派员推荐表.xls";
        replaceModel(data, path, path2);

    }

}