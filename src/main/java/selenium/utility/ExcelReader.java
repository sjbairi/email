package selenium.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import selenium.base.Base;
import selenium.reports.Log;

public class ExcelReader extends Base {

	public static String path = "./Project/ui/uiMaps.xlsx";
	public static FileInputStream fis = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row = null;

	public static void loadExcelData() {
		try {
			File file = new File(path);
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("locators");
			fis.close();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	public static List<Map<String, String>> readAllData() {
		if (sheet == null) {
			loadExcelData();
		}
		List<Map<String, String>> dataList = new ArrayList<>();
		int rows = sheet.getLastRowNum();
		row = sheet.getRow(0);

		for (int j = 0; j < row.getLastCellNum(); j++) {
			Map<String, String> map = new HashMap<>();
			for (int i = 0; i < rows; i++) {
				Row r = CellUtil.getRow(i, sheet);
				String key = CellUtil.getCell(r, 0).getStringCellValue();
				String value = CellUtil.getCell(r, j).getStringCellValue();
				map.put(key, value);
			}
			dataList.add(map);
		}

		return dataList;
	}

	public String getData(String element) {
			String value = null;
			List<Map<String, String>> readAllData = readAllData();
			for (Map<String, String> map : readAllData) 
				value= map.get(element);
				
			return value;
	}
}
