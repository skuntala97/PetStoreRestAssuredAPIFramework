package api.utilies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	static Object[][] data = null;
	static Object[] getSingleColData = null;
	
	public static Object[][]  getDataFromExcel(String filePath, String sheetName) throws Exception { 
		
			FileInputStream fp = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fp);
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			data = new Object[rowCount][colCount];
			DataFormatter formatter = new DataFormatter();
			for(int r=1;r<=rowCount;r++) {
				for(int c=0; c<colCount;c++) {
					XSSFCell cell = sheet.getRow(r).getCell(c);
					data[r-1][c] = formatter.formatCellValue(cell);
				}
			}
			
		workbook.close();
		return data;
	}
	
	public static Object[] getOneColumnData(String filePath,String sheetName, int columNumber) throws Exception {
		FileInputStream fp = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fp);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		DataFormatter formatter = new DataFormatter();
		getSingleColData = new Object[rowCount];
		for(int r=1;r<=rowCount;r++) {
			XSSFCell cell = sheet.getRow(r).getCell(columNumber);
			getSingleColData[r-1] = formatter.formatCellValue(cell);
		}
		workbook.close();
		return getSingleColData;
	}

}
 