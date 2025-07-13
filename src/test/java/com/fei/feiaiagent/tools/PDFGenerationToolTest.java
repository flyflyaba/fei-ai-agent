package com.fei.feiaiagent.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool tool = new PDFGenerationTool();
        String fileName = "旅游智能助手.pdf";
        String content = "你的旅游搭子智能助手 https://github.com";
        String result = tool.generatePDF(fileName, content);
        assertNotNull(result);
    }
}