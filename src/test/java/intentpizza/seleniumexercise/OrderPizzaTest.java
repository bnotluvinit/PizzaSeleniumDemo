package intentpizza.seleniumexercise;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import intentpizza.seleniumexercise.LoginPage;

public class OrderPizzaTest {

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
	public void TestNewPizzaNameSize() {
		// Validates that when creating a new pizza elements are saved and shown
		// on next page

		// Login to app
		LoginPage login = LoginPage.Login(driver);

		Pizza pizza = Pizza.CreatePizzaOrder(driver, "pizza_customer1",
				"size_1");

		String pizza_customer = driver.findElementByXPath("html/body/p[1]")
				.getText();

		Assert.assertEquals(pizza_customer,
				"Name: " + Pizza.getCustomerName("pizza_customer1"));

	}

	@Test
	public void TestOrderPizzaNoToppings() {
		// Validates sending Pizza Order with No Toppings
		LoginPage login = LoginPage.Login(driver);

		Pizza pizza = Pizza.CreatePizzaOrder(driver, "pizza_customer1",
				"size_1");

		// Validate pizza has no toppings
		String toppings = driver.findElementByXPath("html/body/p[3]/b")
				.getText();
		Assert.assertEquals(toppings, "Toppings:");

		// Order this pizza
		driver.findElementByXPath("html/body/p[4]/a[2]").click();

		// Verify taken to order page

		String listing_pizzas = driver.findElementByXPath("html/body/h1")
				.getText();

		Assert.assertEquals(listing_pizzas, "Listing pizzas");
	}

	@Test
	public void TestOrderPizzaWithTopping() {
		// Validates sending Pizza With Toppings
		LoginPage login = LoginPage.Login(driver);

		Pizza pizza = Pizza.CreatePizzaOrder(driver, "pizza_customer2",
				"size_2");

		// Add Topping
		pizza = Pizza.AddTopping(driver, "topping1", "False");

		String topping1 = driver.findElementByXPath(
				"html/body/table/tbody/tr[2]/td[1]").getText();

		Assert.assertEquals(topping1, Pizza.getTopping("topping1"));
	}

	@Test
	public void TestOrderPizzaWithToppings() {
		// Validates sending Pizza with 2 Toppings and Double Order
		LoginPage login = LoginPage.Login(driver);
		Pizza pizza = Pizza.CreatePizzaOrder(driver, "pizza_customer2",
				"size_2");

		Pizza.AddTopping(driver, "topping1", "False");
		Pizza.AddTopping(driver, "topping2", "True");

		String topping1 = driver.findElementByXPath(
				"html/body/table/tbody/tr[2]/td[1]").getText();
		String topping2 = driver.findElementByXPath(
				"html/body/table/tbody/tr[3]/td[1]").getText();
		String double_order2 = driver.findElementByXPath(
				"html/body/table/tbody/tr[3]/td[2]").getText();

		Assert.assertEquals(topping1, Pizza.getTopping("topping1"));
		Assert.assertEquals(topping2, Pizza.getTopping("topping2"));
		Assert.assertEquals(double_order2, "true");

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
		System.out.println(pizza_list);

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
