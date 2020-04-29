package pio;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class test01 {
    /**
     * 将数据输出到文件中
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        /* 创建一个工作部*/
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("测试表");/*指定名称，不指定就是默认，shit1 2 3*/
        for (int i = 1; i <= 9; i++) {
            Row row = sheet.createRow(i - 1);/*创建行*/
            for (int j = 1; j <= i; j++) {
                Cell cell = row.createCell(j - 1);/*创建单元格*/
                cell.setCellValue(j + "x" + i + "=" + (i * j));
            }
        }
        FileOutputStream outputStreamWriter = new FileOutputStream("乘法表.xlsx");
        workbook.write(outputStreamWriter);
        outputStreamWriter.close();
        workbook.close();

    }

    /**
     * 读取到内存中
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        Workbook workbook = new XSSFWorkbook(new FileInputStream("emp.xlsx"));
        Sheet sheetAt = workbook.getSheetAt(0);/*通过索引找到工作部*/
        int lastRowNum = sheetAt.getLastRowNum();/*获取所有行*/
        for (int i = 2; i <= lastRowNum; i++) {
            Row row = sheetAt.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j <= lastCellNum-1; j++) {
                Cell cell = row.getCell(j);
                String stringCellValue = cell.getStringCellValue();/*获取文本*/
                System.out.print(stringCellValue + "  ");
            }
            System.out.println();
        }
    }
}
