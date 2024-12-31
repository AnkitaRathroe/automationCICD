package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.BaseClass;

public class PaymentPage extends BaseClass{
	WebDriver driver ;
	
	@FindBy (css ="[placeholder='Select Country']")
	WebElement selectCountry ; 


	@FindBy(css="section[class*='ta-results']")
	WebElement countryResults ;
	By  countryResult = By.cssSelector("section[class*='ta-results']");
	
	@FindBy (xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectIndia; 
	
	
	@FindBy (css = ".action__submit")
	WebElement submit ;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCountrymethod(String countryname) {
		Actions action = new Actions(driver);
		action.sendKeys(selectCountry, countryname).build().perform() ;
		waitForAppear(countryResult);
		selectIndia.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConfirmationPage(driver);
		
	}

}
