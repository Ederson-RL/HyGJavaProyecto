package com.hyg.proyecto.service.reporteProveedores;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hyg.proyecto.model.Proveedor;


public class ProveedorExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Proveedor> ProveedorList;

    public ProveedorExcel(List<Proveedor> ProveedorList) {
        this.ProveedorList = ProveedorList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Proveedores");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Id", style);
        createCell(row, 1, "nombre", style);
        createCell(row, 2, "correoElectronico", style);
        createCell(row, 3, "telefono", style); 
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
        for (Proveedor Proveedor : ProveedorList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, Proveedor.getId(), style);          
            createCell(row, columnCount++, Proveedor.getNombre(), style);
            createCell(row, columnCount++, Proveedor.getCorreoElectronico(), style);
            createCell(row, columnCount++, Proveedor.getTelefono(), style);
            
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
