/**
 * 
 */
package com.puma.pumaportalLogin;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.puma.testBase.TestBase;


/**
 * @author arvind
 *
 */
public class PumaPortalLogin extends TestBase {

	public static final Logger log = Logger.getLogger(PumaPortalLogin.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//div[@class='utility-menu']/ul/li[4]/a")
	private WebElement btn_SignIn;

	@FindBy(xpath = "//input[@name='socialogin_email']")
	private WebElement input_userName;

	@FindBy(xpath = "//input[@name='socialogin_password']")
	private WebElement input_passWord;

	@FindBy(xpath = "//button[@name='send']")
	private WebElement btn_login;

	@FindBy(xpath = "//div[@class='utility-menu']/ul/li[3]/a")
	private WebElement btn_MyAccount;

	@FindBy(xpath = "//div[@id='login-block']/ul/li[9]/a")
	private WebElement btn_logout;

	@FindBy(xpath = "//div[@class='skip-links-inner']/a[1]")
	private WebElement btn_HomeLogo;

	public PumaPortalLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPageTitle() {
		
		assertEquals("PUMA.COM | Forever Faster", driver.getTitle().trim(), "Expected is equal to actual");
		log("Actual page title is same as expected.");
		return true;
	}



	public void loginToApplication(String username, String password) throws InterruptedException {
		if (btn_SignIn.isDisplayed()) {
			btn_SignIn.click();
			log("Clicked on sign in button, Login window appeared and object is:-" + btn_SignIn.toString());
			Thread.sleep(2000);
		} else {
			log("SignIn button element not present");
		}

		if (input_userName.isDisplayed()) {
			input_userName.clear();
			input_userName.sendKeys(username);
			log("Entered user name:-" + username + " and object is " + input_userName.toString());
			Thread.sleep(1000);
		} else {
			log("UserName element not present");
		}
		if (input_passWord.isDisplayed()) {
			input_passWord.clear();
			input_passWord.sendKeys(password);
			log("Entered password:-" + password + " and object is " + input_passWord.toString());
			Thread.sleep(1000);
		} else {
			log("Password element not present");
		}
		if (btn_login.isDisplayed()) {
			btn_login.click();
			log("cliked on login button and object is:-" + btn_login.toString());
			Thread.sleep(5000);
		} else {
			log("Login button element not present");
		}
	}

	public boolean verifyLoginToPumaPortal() {
		try {

			btn_HomeLogo.isDisplayed();
			log("Home button is dispalyed and object is:-" + btn_HomeLogo.toString());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void logOutFromApplication() throws Exception {
		if (btn_MyAccount.isDisplayed()) {
			btn_MyAccount.click();
			log("clicked on MyAccount button to appear Logout and object is:-" + btn_MyAccount.toString());
			Thread.sleep(1000);
		} else {
			log("MyAccount Button element not present");
		}

		if (btn_logout.isDisplayed()) {
			btn_logout.click();
			log("logout is done and object is:-" + btn_logout.toString());
			Thread.sleep(2000);
		} else {
			log("logout Button element not present");
		}
	}

}
