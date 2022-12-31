package selenium.utility;

import selenium.base.Base;

public class DataProvider extends Base {
	ExcelDataLibrary dataLibrary = new ExcelDataLibrary();

	@org.testng.annotations.DataProvider(name = "facebookpage")
	public Object[][] getLoginData() {
		int row = dataLibrary.getRowCount("facebookpage");
		int col = dataLibrary.getColumnCount("facebookpage");
		int actRows = row - 1;

		Object[][] data = new Object[actRows][col];
		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < col; j++) {
				data[i][j] = dataLibrary.getCellData("facebookpage", j, i + 2);
			}
		}

		return data;
	}
	
	@org.testng.annotations.DataProvider(name="gwdata")
	public Object[][] getGWData(){
		int row = dataLibrary.getRowCount("gwdata");
		int col = dataLibrary.getColumnCount("gwdata");
		int actRows = row - 1;

		Object[][] data = new Object[actRows][col];
		for (int i = 0; i < actRows; i++) {
			for (int j = 0; j < col; j++) {
				data[i][j] = dataLibrary.getCellData("gwdata", j, i + 2);
			}
		}

		return data;
	}

}
