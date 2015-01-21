package intentpizza.seleniumexercise;



import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;

import intentpizza.seleniumexcercise.util.DataProperties;
import intentpizza.seleniumexercise.LoginPage;


public class RemovePizzaTest {
	

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

	public void TestRemoveTopping(){
	//Validates sending Pizza, adding Topping, then removing	
		LoginPage login = LoginPage.Login(driver);

		Pizza pizza = Pizza.CreatePizzaOrder(driver, "pizza_customer2",
				"size_2");

		// Add Topping
		pizza = Pizza.AddTopping(driver, "topping1", "False");

		String topping1 = driver.findElementByXPath(
				"html/body/table/tbody/tr[2]/td[1]").getText();

		//Remove Topping
		driver.findElementByXPath("html/body/table/tbody/tr[2]/td[3]/a").click();
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.RETURN).build().perform();
		
		Assert.assertNotEquals(topping1, Pizza.getTopping("topping1"));
		
		
	

		
	}
	
	
	}
