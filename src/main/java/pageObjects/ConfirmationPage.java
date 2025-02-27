package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.BaseClass;

public class ConfirmationPage extends BaseClass {
	WebDriver driver ;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver, this);
		
	}
	 
	
	@FindBy(css=".hero-primary")
	WebElement confirmMsg ;

	
	public String getConfirmMsg() {
		return confirmMsg.getText();
		
	}
}
