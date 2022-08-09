package com.zhouhao.utils;

import com.zhouhao.pojo.Student;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelOperate {
    /**
     * 创建Excel
     * 
     * @param list
     *            数据
     */
    public static void createExcel(List<Student> list, OutputStream os) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("学生表一");
        
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                2 // last column
        );
        sheet.addMergedRegion(region);
        
        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("学生列表");
        
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
//    	cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        /*
       
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       // cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
       
        HSSFFont font = workbook.createFont();
        font.setFontName("楷体"); //字体
        font.setFontHeightInPoints((short)30); //字体大小
        font.setColor(HSSFColor.RED.index);//颜色
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        font.setItalic(true); //倾斜
        cellStyle.setFont(font);
        */
        headCell.setCellStyle(cellStyle);
        
        
        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("性别");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("爱好");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("出生日期");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("电话");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("备注");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(6);
        headCell.setCellValue("班级");
        headCell.setCellStyle(cellStyle);


        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Student student = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(student.getSname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(student.getSex());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(student.getHobby());
            cell.setCellStyle(cellStyle);

            String strdate = null;
            cell = hssfRow.createCell(3);
            Date date = student.getBirthdate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(date != null) {
                strdate = simpleDateFormat.format(date);
            }
            else{
                strdate = "";
            }
            cell.setCellValue(strdate);
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(student.getPhone());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(student.getReamrk());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(6);
            cell.setCellValue(student.getCname());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
            workbook.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel
     * 
     * @return 数据集合
     */
    private static List<Student> readExcel() {
        List<Student> list = new ArrayList<Student>();
        HSSFWorkbook workbook = null;

        try {
            // 读取Excel文件
            InputStream inputStream = new FileInputStream("D:/students.xls");
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 循环工作表
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                Student student = new Student();

//                HSSFCell cell = hssfRow.getCell(0);
//                if (cell == null) {
//                    continue;
//                }
//                student.setName(cell.getStringCellValue());
//
//                cell = hssfRow.getCell(1);
//                if (cell == null) {
//                    continue;
//                }
//                student.setAge((int) cell.getNumericCellValue());
//
//                cell = hssfRow.getCell(2);
//                if (cell == null) {
//                    continue;
//                }
//                student.setGrade(cell.getStringCellValue());
//
//                list.add(student);
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }
}