package com.jianan.demomodule.test.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.jianan.demomodule.test.json.Param;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jn
 * @Date: 2024/4/3
 * @description
 **/
public class Pdf {
    @Test
    public void readText() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("测试文档.pdf");
        PdfReader reader = new PdfReader(inputStream);
        int pages = reader.getNumberOfPages();
        if(pages > 0){
            for(int i = 1; i <= pages; i++){
                String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                //System.out.println(textFromPage);
            }
        }
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        parser.processContent(1, new TextRenderListener());
    }

    public static void main(String[] args) {
        
        // URL resource = this.getClass().getResource();
        Double a = null;
       // System.out.println(a == 0);


        List<Param> list = new ArrayList<>();
        // print size
        System.out.println(list.size());
        // list增加10个元素
        
        
        
        
        
        Param param = new Param();
        // 默认值

        StringJoiner sj = new StringJoiner("","第","");

        sj.add("16").add("18");
        System.out.println(sj);
    }
    
    @Test
    public void readByPdfBox() throws Exception {
        PDDocument document = Loader.loadPDF(new File("E:\\project\\lijianan\\demo-module\\src\\main\\java\\com\\jianan\\demomodule\\test\\pdf\\测试文档.pdf"));
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.addRegion("name", new Rectangle(0, 0, 100, 100));
        //stripper.setStartPage(0); 
        //stripper.setEndPage(0);
//        stripper.setStartPage(0);
//        stripper.setEndPage(1);
        stripper.extractRegions(document.getPage(0));
        String text = stripper.getTextForRegion("name");
        System.out.println("获取文本内容: " + text);
        document.close();
    }
}
