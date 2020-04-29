package pio;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * easy poi
 */
public class easypoi {
    /**
     * InputStream inputstream,
     * Class<?> pojoClass,
     * ImportParams params
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);/*表头和title格一行*/
        List<PoiEmployee> poiEmployees = ExcelImportUtil.importExcel(new FileInputStream("emp.xlsx"), PoiEmployee.class, importParams);
       /* //导出
        ExportParams exportParams = new ExportParams();
        exportParams.setSheetName("员工列表");
        exportParams.setTitle("员工列表数据");*//*合并单元格*//*
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, PoiEmployee.class, poiEmployees);
        FileOutputStream outputStreamWriter = new FileOutputStream("员工1.xlsx");
        sheets.write(outputStreamWriter);
        outputStreamWriter.close();
        sheets.close();*/

    }

    @Test
    public void testWrite02() throws Exception {
//手动准备数据，下午集成了SpringMVC之后就直接调用Service层方法去查询数据了
        List<PoiEmployee> list = new ArrayList<>();
        list.add(new PoiEmployee("admin1", "admin1@qq.com", 22, "市场部", true));
        list.add(new PoiEmployee("admin2", "admin2@qq.com", 21, "市场部", true));
        list.add(new PoiEmployee("admin3", "admin3@qq.com", 23, "IT部", false));
        list.add(new PoiEmployee("admin4", "admin4@qq.com", 20, "IT部", false));
/**
 * ExportParams entity 导出参数
 * Class<?> pojoClass domain实体类的字节码类型
 * Collection<?> dataSet 数据的集合
 */
        ExportParams params = new ExportParams();
        params.setSheetName("员工列表"); //设置工作表名称
        params.setTitle("员工列表数据"); //设置标题（合并单元格之后居中显示）
//调用工具类的静态方法实现导出，自动循环遍历list集合，将集合中的每一个对象都导出成
        Workbook workbook = ExcelExportUtil.exportExcel(params,
                PoiEmployee.class, list);
//将内容输出到excel文件中
        OutputStream outputStream = new FileOutputStream("员工02.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }


}
