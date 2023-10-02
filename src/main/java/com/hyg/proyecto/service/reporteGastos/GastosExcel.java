package com.hyg.proyecto.service.reporteGastos;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hyg.proyecto.model.Gastos;


public class GastosExcel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Gastos> gastosList;

    public GastosExcel(List<Gastos> gastosList) {
        this.gastosList = gastosList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Gastos");

        // Create title row
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Reporte de Gastos");

        // Style for the title
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCell.setCellStyle(titleStyle);

        // Create header row
        Row headerRow = sheet.createRow(1);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        headerStyle.setFont(font);

        createCell(headerRow, 0, "Id", headerStyle);
        createCell(headerRow, 1, "Fecha", headerStyle);
        createCell(headerRow, 2, "Descripcion", headerStyle);
        createCell(headerRow, 3, "Valor", headerStyle);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else {
            cell.setCellValue(String.valueOf(value));
        }
        cell.setCellStyle(style);

        // Apply blue border to cells
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.LIGHT_BLUE.getIndex());
    }

    private void writeDataLines() {
        int rowCount = 2; // Start from row 3

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);

        for (Gastos gastos : gastosList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, gastos.getId(), style);
            createCell(row, columnCount++, gastos.getFecha().toString(), style);
            createCell(row, columnCount++, gastos.getDescripcion(), style);
            createCell(row, columnCount++, gastos.getValor(), style);
        }

        // Auto-size columns after adding data
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        response.setContentType("application/octet-stream");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}


