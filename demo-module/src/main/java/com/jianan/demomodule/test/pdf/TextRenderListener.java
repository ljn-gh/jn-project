package com.jianan.demomodule.test.pdf;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.LineSegment;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jn
 * @Date: 2024/4/3
 * @description
 **/
public class TextRenderListener implements RenderListener {
    private List<String> list = new ArrayList<>();
    private int index;
    private StringBuilder tem = new StringBuilder();
    public List<String> getList() {
        return list;
    }
    @Override
    public void beginTextBlock() {
        //System.out.println("beginTextBlock");
    }

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        String text = renderInfo.getText();
        if (StringUtils.isBlank(text)) {
            System.out.println("==================");
        }
        System.out.print(text);
        LineSegment baseline = renderInfo.getBaseline();
        Rectangle2D.Float boundingRectange = baseline.getBoundingRectange();
        boundingRectange.getX();
    }

    @Override
    public void endTextBlock() {
        //System.out.println("endTextBlock");
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {

    }
}
