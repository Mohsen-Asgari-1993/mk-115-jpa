package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.repository.impl.CustomerRepositoryImpl;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class JpaApplication {

    @SneakyThrows
    public static void main(String[] args) {

        EntityManager entityManager = ApplicationContext.getInstance().getEntityManager();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(entityManager);
        List<Customer> customers = customerRepository.findAll(
                CustomerSearch.builder()
                        .firstName("m")
                        .build()
        );
//        TODO insert customers in excel file
//        id  firstName  lastName  mobileNumber username
        try (Workbook workbook = new XSSFWorkbook()) {
            CellStyle headerStyle = getHeaderStyle(workbook);
            Sheet sheet = workbook.createSheet();
            insertHeaderData(
                    sheet,
                    new String[]{
                            "id",
                            "first_name",
                            "last_name",
                            "mobile",
                            "username"
                    },
                    headerStyle
            );
            insertData(sheet, customers);
            for (int i = 0; i <= 4; i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream("customers.xlsx")) {
                workbook.write(fileOutputStream);
            }
        }

    }

    private static void insertData(Sheet sheet, List<Customer> customers) {
        int rowNum = 1;
        for (Customer customer : customers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getFirstName() != null ? customer.getFirstName() : "---");
            row.createCell(2).setCellValue(customer.getLastName() != null ? customer.getLastName() : "---");
            row.createCell(3).setCellValue(customer.getMobileNumber() != null ? customer.getMobileNumber() : "---");
            row.createCell(4).setCellValue(customer.getUsername() != null ? customer.getUsername() : "---");
        }
    }

    private static void insertHeaderData(Sheet sheet, String[] headerColumns, CellStyle headerStyle) {
        Row row = sheet.createRow(0);
        int cellNum = 0;
        for (String headerColumn : headerColumns) {
            Cell cell = row.createCell(cellNum++);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headerColumn);
        }
    }

    private static CellStyle getHeaderStyle(Workbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerCellStyle;
    }
}