/**
 * 
 */
package com.puma.TC.productselection;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.puma.productselection.Purchase_of_MenRunningShoes;
import com.puma.pumaportalLogin.PumaPortalLogin;
import com.puma.testBase.TestBase;

/**
 * @author arvind
 *
 */
public class TC_Purchase_Of_MenRunningShoes extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC_Purchase_Of_MenRunningShoes.class.getName());

	
	PumaPortalLogin portallogin;
	Purchase_of_MenRunningShoes product;

	@DataProvider(name = "pumaPortalLoginData")
	public String[][] getTestDataLogin() {
		String[][] testRecordsLogin = getData("TestData.xlsx", "PumaLoginData");
		return testRecordsLogin;
	}

	@DataProvider(name = "ProductSizeQuantityData")
	public String[][] getTestData() {
		String[][] testRecords = getData("TestData.xlsx", "ProductSizeQuantity");
		return testRecords;
	}


	@BeforeClass
	public void setUp() throws IOException {
		init();
		portallogin = new PumaPortalLogin(driver);
		product = new Purchase_of_MenRunningShoes(driver);
	}

	@Test(priority = 1)
	public void tcVerifyPageTitle() {

		try {
			log.info("============= Strting tcVerifyPageTitle===========");

			boolean status = portallogin.verifyPageTitle();

			Assert.assertEquals(status, true);
			log.info("============= Finished tcVerifyPageTitle===========");

			getScreenShot("tcVerifyPageTitle");
		} catch (Exception e) {
			getScreenShot("tcVerifyPageTitle");
		}
	}
	// login test
	@Test(priority = 2, dataProvider = "pumaPortalLoginData")
	public void loginToappWithrequiredCredentials(String username, String password, String runMode) {

		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			log.info("============= Strting login to the puma application===========");
			getScreenShot("Login Page");
			portallogin.loginToApplication(username, password);

			boolean status = portallogin.verifyLoginToPumaPortal();

			Assert.assertEquals(status, true);
			log.info("============= Finished login to the puma application===========");

			getScreenShot("loginToappWithrequiredCredentials");
		} catch (Exception e) {
			getScreenShot("loginToappWithrequiredCredentials");
		}
	}
	@Test(priority = 3)
	public void tcNavigateTo_Men_RunningShoes() {

		try {
			log.info("============= Strting tcNavigateTo_Men_RunningShoes===========");

			product.navigateToMenRunningShoes();

		
			log.info("============= Finished tcNavigateTo_Men_RunningShoes===========");

			getScreenShot("tcNavigateTo_Men_RunningShoes");
		} catch (Exception e) {
			getScreenShot("tcNavigateTo_Men_RunningShoes");
		}
	}
	@Test(priority = 4)
	public void tcClcikOnSecondShoe_AndSwitchToCartPage() {

		try {
			log.info("============= Strting tcClcikOnSecondShoe_AndSwitchToCartPage===========");

			product.clcikOnSecondShoe();

			product.switchToProductDetailsWindow();
			log.info("============= Finished tcClcikOnSecondShoe_AndSwitchToCartPage===========");

			getScreenShot("tcClcikOnSecondShoe_AndSwitchToCartPage");
		} catch (Exception e) {
			getScreenShot("tcClcikOnSecondShoe_AndSwitchToCartPage");
		}
	}
	@Test(priority = 5, dataProvider = "ProductSizeQuantityData")
	public void tcSelectSizeAndQuantity(String shoe_size, String shoe_Quantity, String runMode) {

		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			log.info("============= Strting tcSelectSizeAndQuantity===========");
			
			product.selectSizeAndQuantity(shoe_size, shoe_Quantity);

			log.info("============= Finished tcSelectSizeAndQuantity===========");

			getScreenShot("tcSelectSizeAndQuantity");
		} catch (Exception e) {
			getScreenShot("tcSelectSizeAndQuantity");
		}
	}
	@Test(priority = 6)
	public void tcAddToCartSelectedProduct() {

		try {
			log.info("============= Strting tcAddToCartSelectedProduct===========");

			product.addToCartSelectedProduct();

		
			log.info("============= Finished tcAddToCartSelectedProduct===========");

			getScreenShot("tcAddToCartSelectedProduct");
		} catch (Exception e) {
			getScreenShot("tcAddToCartSelectedProduct");
		}
	}
	@Test(priority = 7, dataProvider = "ProductSizeQuantityData")
	public void tcValidateCartWithProductNamePriceAndQuantity(String shoe_size, String shoe_Quantity, String runMode) {

		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			log.info("============= Strting tcValidateCartWithProductNamePriceAndQuantity===========");
			
			product.validateCartWithProductNamePriceAndQuantity(shoe_size, shoe_Quantity);

			log.info("============= Finished tcValidateCartWithProductNamePriceAndQuantity===========");

			getScreenShot("tcValidateCartWithProductNamePriceAndQuantity");
		} catch (Exception e) {
			getScreenShot("tcValidateCartWithProductNamePriceAndQuantity");
		}
	}
}
