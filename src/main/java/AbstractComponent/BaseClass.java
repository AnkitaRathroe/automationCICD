package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CheckOutPage;
import pageObjects.OrderPage;

public class BaseClass {
	WebDriver driver ;
	
	public BaseClass(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement checkoutbtn ; 
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader ; 

	public void waitForAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void visibilityOfWebElement(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitforDisAppear(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(webElement));
		
	}
	
	public CheckOutPage clickOncheckoutBtn() {
		checkoutbtn.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage ;
		
	}
	
	public OrderPage gotoOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage ;
		
	}


}
