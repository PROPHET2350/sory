package com.example.demo;

import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.regex.Pattern;

@RestController
public class controll {

    @Autowired
    private CompaniesRepository repository;

    @GetMapping("/sory")
    public void a(){
        try {
            FileInputStream file = new FileInputStream("sory.xlsx");
            Workbook workbook = StreamingReader.builder().rowCacheSize(200).bufferSize(4096).open(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String value = getStringCellValue(cell);
                    System.out.println(value + "   ----   index" + cell.getColumnIndex());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getStringCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case FORMULA:
                    try {
                        return NumberToTextConverter.toText(cell.getNumericCellValue());
                    } catch (NumberFormatException e) {
                        return cell.getStringCellValue();
                    }
                case NUMERIC:
                    return NumberToTextConverter.toText(cell.getNumericCellValue());
                case STRING:
                    String cellValue = cell.getStringCellValue().trim();
                    String pattern = "\\^\\$?-?([1-9][0-9]{0,2}(,\\d{3})*(\\.\\d{0,2})?|[1-9]\\d*(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^-?\\$?([1-9]\\d{0,2}(,\\d{3})*(\\.\\d{0,2})?|[1-9]\\d*(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\(\\$?([1-9]\\d{0,2}(,\\d{3})*(\\.\\d{0,2})?|[1-9]\\d*(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))\\)$";
                    if (((Pattern.compile(pattern)).matcher(cellValue)).find()) {
                        return cellValue.replaceAll("[^\\d.]", "");
                    }
                    return cellValue.trim();
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case ERROR:
                    return null;
                default:
                    return cell.getStringCellValue();
            }
        } catch (Exception e) {
            return "";
        }

    }
}
