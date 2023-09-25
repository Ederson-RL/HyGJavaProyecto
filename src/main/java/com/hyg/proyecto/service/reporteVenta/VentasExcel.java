package com.hyg.proyecto.service.reporteVenta;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hyg.proyecto.model.Venta;

import javax.servlet.http.HttpServletResponse;

public class VentasExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Venta> VentaList;

    public VentasExcel(List<Venta> VentaList) {
        this.VentaList = VentaList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Ventas");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Id venta", style);
        createCell(row, 1, "producto", style);
        createCell(row, 2, "fecha", style);
        createCell(row, 3, "Cantidad", style);
        createCell(row, 4, "precio", style); 
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Venta ventas : VentaList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, ventas.getId(), style);          
            createCell(row, columnCount++, ventas.getProducto(), style);
            createCell(row, columnCount++, ventas.getFecha(), style);
            createCell(row, columnCount++, ventas.getCantidad(), style);
            createCell(row, columnCount++, ventas.getPrecio(), style);
            
        }
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        response.setContentType("application/octet-stream");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

}
