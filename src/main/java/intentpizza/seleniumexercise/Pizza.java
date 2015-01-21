package intentpizza.seleniumexercise;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import intentpizza.seleniumexcercise.util.DataProperties;

public class Pizza {

	private FirefoxDriver driver;

	public static String getCustomerName(String name) {
		String pizza_name = DataProperties.getProperty(name);
		return pizza_name;

	}

	public static String getSize(String size) {
		String pizza_size = DataProperties.getProperty(size);
		return pizza_size;
	}

	public static String getTopping(String topping) {
		String pizza_topping = DataProperties.getProperty(topping);
		return pizza_topping;

	}

	public static Pizza CreatePizzaOrder(FirefoxDriver driver,
			String customerName, String Size) {

		// New Pizza
		driver.findElementByXPath("html/body/p[2]/a").click();

		driver.findElementByXPath(".//*[@id='pizza_name']").sendKeys(
				Pizza.getCustomerName(customerName));

		driver.findElementByXPath(".//*[@id='pizza_size']").sendKeys(
				Pizza.getSize(Size));
		// Create Pizza Order

		driver.findElementByXPath(".//*[@id='new_pizza']/p[3]/input").click();

		return PageFactory.initElements(driver, Pizza.class);
	}

	public static Pizza AddTopping(FirefoxDriver driver, String topping,
			String double_order) {
		driver.findElementByXPath("html/body/p[4]/a[1]").click();
		driver.findElementByXPath(".//*[@id='topping_name']").sendKeys(
				Pizza.getTopping(topping));

		if (double_order == "True") {
			driver.findElementByXPath(".//*[@id='topping_double_order']")
					.click();
		}
		driver.findElementByXPath(".//*[@id='new_topping']/p[3]/input").click();

		return PageFactory.initElements(driver, Pizza.class);
	}

}