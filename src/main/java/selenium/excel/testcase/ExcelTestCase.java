package selenium.excel.testcase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import selenium.reports.Log;

public class ExcelTestCase {

	private ExcelTestCase() {

	}

	public static List<Map<String, String>> getTestDetails(String sheetname) {
		List<Map<String, String>> list = null;
		try (FileInputStream tfis = new FileInputStream("./Project/testcase/TestCaseData.xlsx");
				XSSFWorkbook tworkbook = new XSSFWorkbook(tfis)) {
			XSSFSheet tsheet = tworkbook.getSheet(sheetname);
			int lastRowNumber = tsheet.getLastRowNum();
			int lastColNumber = tsheet.getRow(0).getLastCellNum();
			System.out.println("No of Rows: " + tsheet.getLastRowNum() + " and  No of Colums: "
					+ tsheet.getRow(0).getLastCellNum() + " in the sheet: " + tsheet);
			Map<String, String> map = null;
			list = new ArrayList<>();
			for (int i = 1; i <= lastRowNumber; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastColNumber; j++) {
					String key = tsheet.getRow(0).getCell(j).getStringCellValue();
					String value = tsheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}

		} catch (Exception e) {
			Log.error(e.toString());
			e.printStackTrace();
		}
		return list;
	}
}
