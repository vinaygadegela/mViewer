package com.imaginea.tests;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.imaginea.utils.ExcelManager;

/**
 * Sample Test Case to read data from excel sheet
 * 
 * @author krishnakumarnellore
 *
 */
public class DataProviderExample {

    @Test(dataProvider = "getBaseProduct")
    public void test(String testData, String expectedResult) {
        System.out.println("Test Data "+testData + " Expected Result "+expectedResult);
    }

    @DataProvider
    public Iterator<Object[]> getBaseProduct() throws Exception {
        final List<Object[]> list = Lists.newArrayList();
        ExcelManager excelManager = new ExcelManager("src/main/resources/mviewertestcases.xls");
        String sheetName = "mviewer";
        int totalRowCount = excelManager.getRowCount(sheetName);
        int totalColCount = excelManager.getColumnCount(sheetName);
        String testData;
        String expectedResult = null;
        for (int i = 2; i < totalRowCount + 1; i++) { // It will skip 1st row
            testData = excelManager.getCellData(sheetName, 4, i); // 4 refer to column of Test Data
                                                                  
            for (int j = 1; j < totalColCount; j++) {
                expectedResult = excelManager.getCellData(sheetName, 5, i); // 5 refer to column of expected result
            }

            list.add(new Object[] { testData, expectedResult });
        }
        return list.iterator();
    }

}
