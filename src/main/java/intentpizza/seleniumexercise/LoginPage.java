package intentpizza.seleniumexercise;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import intentpizza.seleniumexcercise.util.DataProperties;

public class LoginPage {
	private FirefoxDriver driver;

	public static LoginPage navigateTo(FirefoxDriver driver) {
		// URL TapHandleURL= new
		// URL(DataProperties.getProperty("TapHandleURL"));

		driver.get("http://intent-pizza.internal.intentmedia.net:8080/");
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public static String getbadlogin() {
		String invalid_login = DataProperties.getProperty("invalid_login");
		System.out.println(invalid_login);
		return invalid_login;
	}

	public static String getbadpassword() {
		String invalid_password = DataProperties
				.getProperty("invalid_password");
		System.out.println(invalid_password);
		return invalid_password;
	}

	public static String getgoodlogin() {
		String valid_login = DataProperties.getProperty("valid_login");
		System.out.println(valid_login);
		return valid_login;
	}

	public static String getgoodpassword() {
		String valid_password = DataProperties.getProperty("valid_password");
		System.out.println(valid_password);
		return valid_password;
	}

	public static LoginPage Login(FirefoxDriver driver) {
		LoginPage login = LoginPage.navigateTo(driver);
		// Use good login/password

		driver.findElementByXPath(".//*[@id='user_session_email']").sendKeys(
				LoginPage.getgoodlogin());

		driver.findElementByXPath(".//*[@id='user_session_password']")
				.sendKeys(LoginPage.getgoodpassword());
		// Attempt to login
		driver.findElementByXPath(".//*[@id='new_user_session']/input[3]")
				.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

}
