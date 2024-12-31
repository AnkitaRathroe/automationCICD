package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.BaseClass;

public class LandingPage extends BaseClass{

	WebDriver driver ;
	
public LandingPage(WebDriver driver) {
	super(driver);
	this.driver = driver ;
	PageFactory.initElements(driver, this);
}
	//WebElement gmail  =	driver.findElement(By.xpath("//input[@id='userEmail']"));
	//	WebElement password = driver.findElement(By.xpath("//input[@id='userPassword']"));
	//WebElement loginBtn = 	driver.findElement(By.id("login"));
	
	@FindBy(xpath = "//input[@id='userEmail']") 
	WebElement gmailElement ;
	
	@FindBy(xpath =  "//input[@id='userPassword']")
	WebElement passwordElement ;
	
	@FindBy(xpath = "//div[@id='toast-container'] //div")
	WebElement erroMsg ;
	
	public String getErrorMsg() {
		visibilityOfWebElement(erroMsg);
		System.out.println( erroMsg.getText().toString());
		return erroMsg.getText().toString() ;
	}
	
	
	@FindBy(id="login")
	WebElement loginBtn ;
	
	public ProductCatelog loginApplication(String gmail , String password) {
		gmailElement.sendKeys(gmail);
		passwordElement.sendKeys(password);
		loginBtn.click();
		ProductCatelog catelog = new ProductCatelog(driver);
		return catelog ;
	}
	
//	public void gotopage() {
//		driver.get("https://rahulshettyacademy.com/client");
//		
//	}

}
