package selenium.excel.testcase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class MethodInterceptor implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		String description = null;
		List<Map<String, String>> list = ExcelTestCase.getTestDetails("testsheet");
		List<IMethodInstance> results = new ArrayList<>();
		for (int i = 0; i < methods.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("Test Case")) && list.get(j).get("Execute").equalsIgnoreCase("y")) {
					methods.get(i).getMethod().setDescription(list.get(j).get("Description"));
					description = methods.get(i).getMethod().getDescription();
					results.add(methods.get(i));
				}
			}
		}
		return results;
	}
}
