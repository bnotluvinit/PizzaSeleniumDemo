package intentpizza.seleniumexercise;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class LoginPageTest {

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
	public void TestInvalidLogin(){
		//Open IntentPizza Homepage
		
		LoginPage login = LoginPage.navigateTo(driver);
		//Use bad invalid login/password
		
		
		driver.findElementByXPath(".//*[@id='user_session_email']").sendKeys(LoginPage.getbadlogin());
		
		driver.findElementByXPath(".//*[@id='user_session_password']").sendKeys(LoginPage.getbadpassword());
		//Attempt to login
		driver.findElementByXPath(".//*[@id='new_user_session']/input[3]").click();
		
		String invalid_login_error = driver.findElementByXPath(".//*[@id='error_explanation']/ul/li").getText();
		
		Assert.assertEquals(invalid_login_error, "Email is not valid");
		
	}
	
	@Test
	public void TestValidLogin(){
		//Open IntentPizza Homepage
		LoginPage login = LoginPage.Login(driver);
		String login_name = driver.findElementByXPath("html/body/p[1]").getText();
		
		Assert.assertEquals(login_name, "Login: " + LoginPage.getgoodlogin());
	}
	
}