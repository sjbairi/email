package selenium.excel.testcase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProvider extends MethodInterceptor {
	public static List<Map<String, String>> list = new ArrayList<>();

	@org.testng.annotations.DataProvider
	public static Object[] getData(Method m) {
		if (list.isEmpty()) {
			list = ExcelTestCase.getTestDetails("TestData");

		}
		List<Map<String, String>> iterationlist = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Test Case").equalsIgnoreCase(m.getName())
					&& list.get(i).get("Execute").equalsIgnoreCase("Y")) {
				iterationlist.add(list.get(i));
			}
		}
		return iterationlist.toArray();
	}
}
