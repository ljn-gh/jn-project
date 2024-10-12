package com.jianan.demomodule.test.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.util.filetypedetector.FileType;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String old = "D:\\hyjx\\fileupload\\file\\4636d6dacd1d45a595708b6e47f860c5\\签名.pdf";
        String n = "D:\\hyjx\\fileupload\\file\\4636d6dacd1d45a595708b6e47f860c5\\n.pdf";
        mergePdfWithImage(List.of(old,old),new FileOutputStream(n));
    }

    private static void fillImage(PDDocument document, BufferedImage bufferedImage) throws Exception {
        PDPage pdPage = new PDPage(new PDRectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));
        document.addPage(pdPage);
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, FileType.JPEG.name(), byteArray);
        PDImageXObject pdImageXObject = PDImageXObject.createFromByteArray(document, byteArray.toByteArray(), FileType.JPEG.name());
        PDPageContentStream contentStream = new PDPageContentStream(document, pdPage);
        contentStream.drawImage(pdImageXObject, 0, 0);
        contentStream.close();
        byteArray.close();
    }

    private static void mergePdfWithImage(List<String> pathList, OutputStream out) throws Exception {
        if(CollectionUtils.isEmpty(pathList)){
            return;
        }
        PDDocument document = new PDDocument();
        for (String path : pathList) {
            PDDocument pdDocument = Loader.loadPDF(new File(path));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            for (int i = 0; i < pdDocument.getNumberOfPages(); i++) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i,200);
                fillImage(document,bufferedImage);
            }
            pdDocument.close();
        }
        document.save(out);
        document.close();
    }
}
