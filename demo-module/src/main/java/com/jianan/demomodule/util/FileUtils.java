package com.jianan.demomodule.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author: jn
 * @Date: 2024/12/1
 * @description
 **/
public class FileUtils {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("D:\\SM2Util.java");
        Files.copy(inputStream, Paths.get("D:\\2.java"), StandardCopyOption.REPLACE_EXISTING);
    }
}
