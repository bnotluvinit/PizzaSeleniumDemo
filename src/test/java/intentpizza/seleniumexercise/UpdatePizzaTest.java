package intentpizza.seleniumexercise;


import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import intentpizza.seleniumexercise.LoginPage;

public class UpdatePizzaTest {

	private FirefoxDriver driver;

	@Before
	public void setupSelenium() {
		driver = new FirefoxDriver();
	}

	@After
	public void closeSelenium() {
		driver.close();
		driver.quit();
	}

	@Test
	public void TestUpdatePizzaSize() {
		// Validates can update Pizza Order Size after Selection
		// Login to site
		LoginPage login = LoginPage.Login(driver);
		Pizza pizza = Pizza.CreatePizzaOrder(driver, "pizza_customer3",
				"size_3");
		// Add Toppings
		Pizza.AddTopping(driver, "topping1", "False");
		Pizza.AddTopping(driver, "topping2", "True");

		driver.findElementByXPath("html/body/p[5]/a").click();

		// Find Pizza just selected
		WebElement pizza_table = driver.findElementByXPath("//html/body/table");
		int pizza_list = pizza_table.findElements(By.xpath("//table/tbody/tr"))
				.size() - 1;
		

		// Edit new pizza
		driver.findElementByXPath(
				"//html/body/table/tbody/tr[" + pizza_list + "]/td[6]/a")
				.click();

		driver.findElementByXPath(".//*[@id='pizza_size']").clear();
		driver.findElementByXPath(".//*[@id='pizza_size']").sendKeys(
				Pizza.getSize("size_2"));

		driver.findElementByName("commit").click();

		Assert.assertEquals("Size: " + Pizza.getSize("size_2"), driver
				.findElementByXPath("html/body/p[2]").getText());

	}

}
