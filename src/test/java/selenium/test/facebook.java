package selenium.test;

import org.testng.annotations.Test;

import selenium.base.Base;

public class facebook extends Base{
	@Test
	public void loginFaceBook() {
		driver.get("www.facebook.com");
		
	}

}
