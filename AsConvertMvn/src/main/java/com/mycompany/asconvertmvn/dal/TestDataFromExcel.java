///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.asconvertmvn.dal;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
///**
// *
// * @author admin
// */
//public class TestDataFromExcel {
//
//    public static final int PRODUCT_ID_COL = 1;
//    public static final int PRODUCT_NAME_COL = 2;
//    public static final int CATEGORY_ID_COL = 3;
//    public static final int QUANTITY_COL = 4;
//    public static final int SALE_COL = 5;
//    public static final int PRICE_COL = 6;
//    public static final int IMAGE_COL = 7;
//    public static final int EXRESULT_COL = 8;
//
//    public static Object[][] cloneData(String excelPath, int rows) throws Exception {
//        Object[][] data = new Object[rows][EXRESULT_COL];
//        InputStream inputStream = new FileInputStream(new File(excelPath));
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//        for (int i = 1; i <= rows; i++) {
//            Row row = sheet.getRow(i);
//            for (int j = 0; j < EXRESULT_COL; j++) {
//                Cell cell = row.getCell(j);
//                if (cell != null) {
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            data[i - 1][j] = cell.getStringCellValue();
//                            break;
//                        case NUMERIC:
//                            data[i - 1][j] = cell.getNumericCellValue();
//                            break;
//                        // Thêm các trường hợp khác nếu cần
//                        default:
//                            data[i - 1][j] = null;
//                    }
//                }
//            }
//        }
//        workbook.close();
//        inputStream.close();
//        return data;
//    }
//
//    public static void main(String[] args) {
//        Object[][] data = new Object[2][EXRESULT_COL];
//        try {
//            data = cloneData("C:/FU-Learning/Su24/SWT301/Book1.xlsx", 2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(data);
//    }
//}
