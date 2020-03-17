package com.thinkgem.jeesite.common.utils.word;

import org.apache.poi.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 白子涵
 * 自定义 XWPFDocument，并重写 createPicture()方法
 */
public class CustomXWPFDocument extends XWPFDocument {

    protected static final POIXMLRelation[] RELATIONS;

    static {
        RELATIONS = new POIXMLRelation[8];
        RELATIONS[Document.PICTURE_TYPE_EMF] = XWPFRelation.IMAGE_EMF;
        RELATIONS[Document.PICTURE_TYPE_WMF] = XWPFRelation.IMAGE_WMF;
        RELATIONS[Document.PICTURE_TYPE_PICT] = XWPFRelation.IMAGE_PICT;
        RELATIONS[Document.PICTURE_TYPE_JPEG] = XWPFRelation.IMAGE_JPEG;
        RELATIONS[Document.PICTURE_TYPE_PNG] = XWPFRelation.IMAGE_PNG;
        RELATIONS[Document.PICTURE_TYPE_DIB] = XWPFRelation.IMAGE_DIB;
    }

    public CustomXWPFDocument(InputStream in) throws IOException {
        super(in);
    }

    public CustomXWPFDocument() {
        super();
    }

    public CustomXWPFDocument(OPCPackage pkg) throws IOException {
        super(pkg);
    }

    /**
     * @param id
     * @param width     宽
     * @param height    高
     * @param paragraph 段落
     */
    public void createPicture(int id, int width, int height, XWPFParagraph paragraph) {
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        String blipId = getAllPictures().get(id).getPackageRelationship().getId();
        CTInline inline = paragraph.createRun().getCTR().addNewDrawing().addNewInline();

        System.out.println(blipId + ":" + inline);

        String picXml = ""
                + "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
                + "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
                + "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
                + "         <pic:nvPicPr>" + "            <pic:cNvPr id=\""
                + id
                + "\" name=\"Generated\"/>"
                + "            <pic:cNvPicPr/>"
                + "         </pic:nvPicPr>"
                + "         <pic:blipFill>"
                + "            <a:blip r:embed=\""
                + blipId
                + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
                + "            <a:stretch>"
                + "               <a:fillRect/>"
                + "            </a:stretch>"
                + "         </pic:blipFill>"
                + "         <pic:spPr>"
                + "            <a:xfrm>"
                + "               <a:off x=\"0\" y=\"0\"/>"
                + "               <a:ext cx=\""
                + width
                + "\" cy=\""
                + height
                + "\"/>"
                + "            </a:xfrm>"
                + "            <a:prstGeom prst=\"rect\">"
                + "               <a:avLst/>"
                + "            </a:prstGeom>"
                + "         </pic:spPr>"
                + "      </pic:pic>"
                + "   </a:graphicData>" + "</a:graphic>";

        inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        try {
            xmlToken = XmlToken.Factory.parse(picXml);
        } catch (XmlException xe) {
            xe.printStackTrace();
        }
        inline.set(xmlToken);

        inline.setDistT(0);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);

        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);

        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("图片" + id);
        docPr.setDescr("测试");
    }


    public int addPicture(InputStream is, int format) throws IOException, InvalidFormatException {
        int imageNumber = getNextPicNameNumber(format);
        XWPFPictureData img = (XWPFPictureData) createRelationship(RELATIONS[format], XWPFFactory.getInstance(), imageNumber, false);
        OutputStream out = img.getPackagePart().getOutputStream();
        IOUtils.copy(is, out);
        out.close();
        pictures.add(img);
        return getAllPictures().size() - 1;
    }

}