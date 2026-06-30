package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtils {

    public static void writeTerraData(List<String> items) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Terra Collection");

            int rowNum = 0;

            // Header
            XSSFRow header = sheet.createRow(rowNum++);
            header.createCell(0).setCellValue("Section");
            header.createCell(1).setCellValue("Item");

            // Data
            for (String item : items) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue("Terra Collection");
                row.createCell(1).setCellValue(item);
            }

            // File
            String path = System.getProperty("user.dir") + "/src/test/resources/testData/TerraCollection.xlsx";
            FileOutputStream file = new FileOutputStream(path);
            workbook.write(file);

            workbook.close();
            file.close();

            LoggerManager.info("Excel file created successfully ✅");

        } catch (Exception e) {
            LoggerManager.error("Excel writing failed: " + e.getMessage());
        }
    }
}

