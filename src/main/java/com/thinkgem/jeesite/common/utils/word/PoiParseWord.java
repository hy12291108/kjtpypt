
package com.thinkgem.jeesite.common.utils.word;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by 白子涵
 * POI解析WORD文档
 */
public class PoiParseWord {

    /**
     * Word2003版本后缀名
     */
    private final static String DOC = "doc";

    /**
     * Word2007以上版本后缀名
     */
    private final static String DOCX = "docx";

    /**
     * 系统临时文件路径
     */
    private final static String SYSTEM_TEMPORARY_PATH = System.getProperty("java.io.tmpdir");

    /**
     *
     */
    private OPCPackage oPCPackage;
    private XWPFDocument xwpfDocument;

    private FileInputStream fileInputStream;
    private POIFSFileSystem poifsFileSystem;
    private HWPFDocument hwpfDocument;

    public PoiParseWord(File file) {
        String suffixName = file.getPath().split("\\.")[1];
        try {
            if (DOCX.equals(suffixName)) {
                oPCPackage = POIXMLDocument.openPackage(file.getPath());
                xwpfDocument = new XWPFDocument(oPCPackage);
            }
            if (DOC.equals(suffixName)) {
                fileInputStream = new FileInputStream(file);
                poifsFileSystem = new POIFSFileSystem(fileInputStream);
                hwpfDocument = new HWPFDocument(poifsFileSystem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出word文档表格第cellRowIdx行，第cellColIdx列的值（DOCX）
     *
     * @param cellRowIdx
     *            行
     * @param cellColIdx
     *            列
     */
    @SuppressWarnings("unused")
    public String getSpecifyDataForDocx(int cellRowIdx, int cellColIdx) {
        try {
            // 获取页面中的表格
            Iterator<XWPFTable> it = xwpfDocument.getTablesIterator();
            while (it.hasNext()) {
                // 循环页面中的表格
                XWPFTable table = (XWPFTable) it.next();
                StringBuffer str = new StringBuffer();
                // 获取表格中的行
                XWPFTableRow row = table.getRow(cellRowIdx);
                // 获取行中共有多少列
                List<XWPFTableCell> cells = row.getTableCells();
                // 获取列
                XWPFTableCell cell = cells.get(cellColIdx);
                // 获取列中的段落
                StringBuffer allFilePath = new StringBuffer();
                for (int i = 0; i < cell.getParagraphs().size(); i++) {
                    List<XWPFRun> runs = cell.getParagraphs().get(i).getRuns();
                    for (int j = 0; j < runs.size(); j++) {
                        // 获取单个对象
                        XWPFRun r = runs.get(j);
                        String text = r.getText(r.getTextPosition());
                        // 如果字符为空，可能是附件一类的文件，比如图片之类的，需要另外解析,此处处理为图片
                        if (text == null) {
                            List<XWPFPicture> piclist = r.getEmbeddedPictures();
                            for (int k = 0; k < piclist.size(); k++) {
                                String filePath = SYSTEM_TEMPORARY_PATH + UUID.randomUUID() + ".jpg";
                                XWPFPicture pic = piclist.get(k);
                                byte[] picbyte = pic.getPictureData().getData();
                                // 将图片写入本地文件
                                @SuppressWarnings("resource")
                                FileOutputStream fos = new FileOutputStream(filePath);
                                fos.write(picbyte);
                                allFilePath.append(filePath);
                            }
                        } else {
                            allFilePath.append(text);
                        }
                    }
                }
                return allFilePath.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭
     */
    public void closeForDocx() {
        try {
            oPCPackage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出word文档表格第cellRowIdx行，第cellColIdx列的值（DOC）
     *
     * @param cellRowIdx
     *            行
     * @param cellColIdx
     *            列
     */
    @SuppressWarnings({ "resource", "unused" })
    public String getSpecifyDataForDoc(int cellRowIdx, int cellColIdx) {
        try {
            Range range = hwpfDocument.getRange();
            TableIterator it = new TableIterator(range);
            PicturesTable picturesTable = hwpfDocument.getPicturesTable();
            while (it.hasNext()) {
                Table tb = (Table) it.next();
                TableRow tr = tb.getRow(cellRowIdx);
                TableCell td = tr.getCell(cellColIdx);
                CharacterRun cr = td.getCharacterRun(0);
                if (picturesTable.hasPicture(cr)) {
                    Picture pic = picturesTable.extractPicture(cr, true);
                    byte[] picbyte = pic.getContent();
                    String filePath = SYSTEM_TEMPORARY_PATH + UUID.randomUUID() + ".jpg";
                    // 将图片写入本地文件
                    FileOutputStream fos = new FileOutputStream(filePath);
                    fos.write(picbyte);
                    return filePath;
                } else {
                    for (int k = 0; k < td.numParagraphs(); k++) {
                        Paragraph para = td.getParagraph(k);
                        String s = para.text();
                        s = s.substring(0, s.length() - 1);
                        return s;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}