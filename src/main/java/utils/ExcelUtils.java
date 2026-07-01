package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    /**
     * Load Excel File and Sheet
     */
    public static void setExcelFile(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println("Error loading Excel file: " + e.getMessage());
        }
    }

    /**
     * Get cell data as String
     */
    public static String getCellData(int rowNum, int colNum) {
        try {
            Cell cell = sheet.getRow(rowNum).getCell(colNum);

            if (cell == null) {
                return "";
            }

            switch (cell.getCellType()) {

                case STRING:
                    return cell.getStringCellValue();

                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toString();
                    } else {
                        return String.valueOf((long) cell.getNumericCellValue());
                    }

                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case BLANK:
                    return "";

                default:
                    return "";
            }

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get total rows
     */
    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    /**
     * Get total columns
     */
    public static int getColumnCount(int rowNum) {
        return sheet.getRow(rowNum).getLastCellNum();
    }
}
