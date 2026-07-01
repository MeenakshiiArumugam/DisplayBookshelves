package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtils {

    public static void writeBookshelfData(List<String> names, List<String> prices) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Bookshelves");
            //Header Row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("S.No");
            header.createCell(1).setCellValue("Product Name");
            header.createCell(2).setCellValue("Price");
            //Data Rows
            for (int i = 0; i < names.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(names.get(i));
                row.createCell(2).setCellValue(prices.get(i));
            }
            //Save file
            FileOutputStream fileOut = new FileOutputStream("Bookshelves.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Data written to Excel successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}