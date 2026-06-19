package api.utilies;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="GetAllUsersData")
	public Object[][] getAllUserDataProvider() throws Exception {
		String fileName = System.getProperty("user.dir")+"\\src\\test\\resources\\api\\testData\\TestData.xlsx";
		String sheetName = "Sheet2";
		return ExcelUtils.getDataFromExcel(fileName, sheetName);
	}
	
	@DataProvider(name="GetUserName") 
	public Object[] getAllUserNameDataProvider() throws Exception {
		String fileName = System.getProperty("user.dir")+"\\src\\test\\resources\\api\\testData\\TestData.xlsx";
		String sheetName = "Sheet2";
		int colNumber = 1;
		return ExcelUtils.getOneColumnData(fileName, sheetName, colNumber);
	}

}
