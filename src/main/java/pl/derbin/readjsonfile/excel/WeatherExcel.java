package pl.derbin.readjsonfile.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.derbin.readjsonfile.entity.Weather;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class WeatherExcel {
    private static final String[] header = {"Id", "name", "country", "main", "description"};
    private static final String SHEET = "Weathers";

    public static ByteArrayInputStream weatherToExcel(List<Weather> weatherList) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < header.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header[col]);
            }
            int rowIdx = 1;
            for (Weather weather : weatherList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(weather.getId());
                row.createCell(1).setCellValue(weather.getName());
                row.createCell(2).setCellValue(weather.getCountry());
                row.createCell(3).setCellValue(weather.getMain());
                row.createCell(4).setCellValue(weather.getDescription());
            }
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            throw new RuntimeException("fail to import data to Excel file: " + ex.getMessage());
        }
    }
}
