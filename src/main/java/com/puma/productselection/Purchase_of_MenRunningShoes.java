/**
 * 
 */
package com.puma.productselection;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.puma.testBase.TestBase;

/**
 * @author arvind
 *
 */
public class Purchase_of_MenRunningShoes extends TestBase {

	public static final Logger log = Logger.getLogger(Purchase_of_MenRunningShoes.class.getName());

	WebDriver driver;
	Select select;
	WebElement option;
	String productName;
	String price;
	String parentWindow;

	@FindBy(xpath = "//div[@id='header-nav']/ul/li[1]/a")
	private WebElement btn_Men;

	@FindBy(xpath = "//li[@id='men-subnav']//div[2]/ul/li[2]/a")
	private WebElement btn_RunningShoes;

	@FindBy(xpath = "//div[@class='category-products']/ul[1]/li[2]")
	private WebElement btn_SecondShoes;

	@FindBy(xpath = "(//div[@class='product-info']/h2/a)[2]")
	private WebElement productName2ndShoesMainPage;

	@FindBy(xpath = "(//div[@class='price-box']//span[2]/span)[2]")
	private WebElement price2ndShoeMainPage;

	// @FindBy(xpath = "(//div[@class='product-info']/h2/a)[2]")
	// private WebElement productName2ndShoesMainPage;
	//
	// @FindBy(xpath = "(//div[@class='price-box']//span[2]/span)[1]")
	// private WebElement price2ndShoeCartPage;

	@FindBy(xpath = "//div[@id='size_label']/div//following-sibling::div[2]")
	private WebElement btn_Size;

	@FindBy(xpath = "//div[@class='product-size-div']/ul/li[2]/a")
	private WebElement select_Size;
	
	@FindBy(xpath = "//div[@class='product-size-div']/ul/li[2]/a/span[1]")
	private WebElement selected_Size;
	

	@FindBy(xpath = "//div[@class='add-to-cart-qty']/select")
	private WebElement sel_quantity;
	
	@FindBy(xpath = "//div[@class='add-to-cart']//button")
	private WebElement btn_AddToCart;

	@FindBy(xpath = "//table[@id='shopping-cart-table']/tbody/tr/td[2]/h2/a")
	private WebElement productNameCartPage;

	@FindBy(xpath = "//table[@id='shopping-cart-table']/tbody/tr/td[3]/span/span")
	private WebElement unitPriceCartPage;

	@FindBy(xpath = "//table[@id='shopping-cart-table']/tbody/tr/td[4]/div/select")
	private WebElement sel_QuantityCart;

	public Purchase_of_MenRunningShoes(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToMenRunningShoes() throws Exception {
		if (btn_Men.isDisplayed()) {
			Actions action = new Actions(driver);
			action.moveToElement(btn_Men).perform();
			Thread.sleep(1000);
			action.moveToElement(btn_RunningShoes);
			action.click();
			action.perform();
			log("Clicked on men running shoes.");
			Thread.sleep(1000);
		}else {
			log("Men element is not present.");
			Thread.sleep(500);
		}
	}

	public void clcikOnSecondShoe() throws Exception{
		if(btn_SecondShoes.isDisplayed()){
		productName = productName2ndShoesMainPage.getText().trim();
		price = price2ndShoeMainPage.getText().trim();
		btn_SecondShoes.click();
		log("clicked on 2nd shoes and new window lauunch for carting and object is:-"+btn_SecondShoes.toString());
		Thread.sleep(1000);
		}else{
			log("Second shoe element is not present and object is:-"+btn_SecondShoes.toString());
			Thread.sleep(500);
		}
	}
	public void switchToProductDetailsWindow() throws Exception{
		parentWindow = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    log("Window switch to cart window.");
		    Thread.sleep(1000); 
		}  
	}
	
	public void switchWindowBackToMainWindow() throws Exception{
		driver.close();
		Thread.sleep(1000);
		// Switch back to original browser (first window)
		driver.switchTo().window(parentWindow);
	}
	public void selectSizeAndQuantity(String shoe_size, String shoe_Quantity) throws Exception{
		
		if(btn_Size.isDisplayed()){
			btn_Size.click();
			log("Clicked on size selection button and size list panel appeared");
			Thread.sleep(1000);
		}
			else{
				log("Size button element is not present and object is:-"+btn_Size.toString());
				Thread.sleep(500);
			}	
		if(select_Size.isDisplayed()){
			select_Size.click();
			log("Clicked on specific size and selected size appear in the box.");
			Thread.sleep(1000);
		}
			else{
				log("Size select element is not present and object is:-"+select_Size.toString());
				Thread.sleep(500);
			}	
	
		
		select = new Select(selected_Size);
		Thread.sleep(1000);
		option = select.getFirstSelectedOption();
		Assert.assertEquals(option.getText().trim(), shoe_size);
		log("Selected shoe size is same as entered.");
		Thread.sleep(1000);
		
		if(sel_quantity.isDisplayed()){
		select = new Select(sel_quantity);
		select.selectByVisibleText(shoe_Quantity);
		log("Entered shoe Quantity:-"+shoe_Quantity);
		Thread.sleep(1000);
		option = select.getFirstSelectedOption();
		Assert.assertEquals(option.getText().trim(), shoe_Quantity);
		log("Selected shoe Quantity is same as entered.");
		Thread.sleep(1000);
		}
		else{
			log("Select quantity element is not p[resent and object is:-"+sel_quantity.toString());
			Thread.sleep(500);
		}
	}
	public void addToCartSelectedProduct() throws Exception{
		
		if(btn_AddToCart.isDisplayed()){
		btn_AddToCart.click();
		log("Click on add to cart button to add product in cart and object is:-"+btn_AddToCart.toString());
		Thread.sleep(1000);
		}else{
			log("Add to cart button element not present and object is:-"+btn_AddToCart.toString());
			Thread.sleep(500);
		}
	}
	public void validateCartWithProductNamePriceAndQuantity(String shoe_size, String shoe_Quantity) throws Exception{
		assertEquals("Shopping Cart", driver.getTitle().trim(), "Expected is equal to actual");
		log("Actual page tiltle for cart is same as expected.");
		Thread.sleep(1000);
		
		assertEquals(productName, productNameCartPage.getText().trim());
		log("Product name appear on main page is same as Cart page and object is:-"+productNameCartPage.toString());
		Thread.sleep(1000);
		
		assertEquals(price, unitPriceCartPage.getText().trim());
		log("Product Price appear on main page is same as Cart page and object is:-"+unitPriceCartPage.toString());
		Thread.sleep(1000);
		
		select = new Select(sel_QuantityCart);
		option = select.getFirstSelectedOption();
		Assert.assertEquals(option.getText().trim(), shoe_Quantity);
		log("Selected shoe Quantity is same in the Cart page.");
		Thread.sleep(2000);
	}
}
