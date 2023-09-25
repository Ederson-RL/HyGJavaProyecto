package com.hyg.proyecto.service.reporteCompra;

import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hyg.proyecto.model.Compras;
import javax.servlet.http.HttpServletResponse;


public class CompraExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Compras> CompraList;

    public CompraExcel(List<Compras> CompraList) {
        this.CompraList = CompraList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Compras");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID Compras", style);
        createCell(row, 1, "Fecha", style);
        createCell(row, 2, "Proveedor", style);
        createCell(row, 3, "Producto", style);
        createCell(row, 4, "Cantidad", style);
        createCell(row, 5, "precio", style); 
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
        for (Compras compras : CompraList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, compras.getIdcompra(), style);
            createCell(row, columnCount++, compras.getFechaC(), style);
            createCell(row, columnCount++, compras.getProveedorC(), style);
            createCell(row, columnCount++, compras.getPrecioC(), style);
            createCell(row, columnCount++, compras.getCantidadC(), style);
            createCell(row, columnCount++, compras.getPrecioC(), style);
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
