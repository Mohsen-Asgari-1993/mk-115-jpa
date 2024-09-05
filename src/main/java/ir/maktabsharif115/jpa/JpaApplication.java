package ir.maktabsharif115.jpa;

import ir.maktabsharif115.jpa.domain.Customer;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JpaApplication {

    @SneakyThrows
    public static void main(String[] args) {

        try (Workbook workbook = new XSSFWorkbook(new File("customers.xlsx"))) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum >= 1) {
                List<Customer> customers = new ArrayList<>();
                for (int rowNumber = 0; rowNumber <= lastRowNum; rowNumber++) {
                    Row row = sheet.getRow(rowNumber);
                    short lastCellNum = row.getLastCellNum();
                    if (lastCellNum >= 1) {
//                        StringBuilder currentRow = new StringBuilder();
//                        for (int cellNumber = 0; cellNumber < lastCellNum; cellNumber++) {
//                            currentRow.append(getCellStringValue(row.getCell(cellNumber)))
//                                    .append(" --- ");
//                        }
//                        System.out.println(currentRow);
                        Customer customer = new Customer();
                        customer.setId(
                                Long.getLong(getCellStringValue(row.getCell(0)))
                        );
                        customer.setFirstName(getCellStringValue(row.getCell(1)));
                        customer.setLastName(getCellStringValue(row.getCell(2)));
                        customer.setMobileNumber(getCellStringValue(row.getCell(3)));
                        customer.setUsername(getCellStringValue(row.getCell(4)));
                        customers.add(customer);
                    }
                }
                customers.forEach(
                        c -> System.out.println(c.getId() + " - " + c.getFirstName())
                );
            }
        }
    }

    public static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (CellType.STRING.equals(cell.getCellType()) || CellType.BLANK.equals(cell.getCellType())) {
            return cell.getStringCellValue().trim();
        } else if (CellType.NUMERIC.equals(cell.getCellType())) {
            return new DecimalFormat("###").format(cell.getNumericCellValue()).trim();
        } else if (CellType.FORMULA.equals(cell.getCellType())) {
            return cell.getCellFormula().trim();
        }
        throw new RuntimeException("wrong cell value");
    }

}