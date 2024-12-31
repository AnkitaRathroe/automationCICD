package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import AbstractComponent.BaseClass;

public class CheckOutPage extends BaseClass {

	WebDriver driver ;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver= driver ;
	}
	
@FindBy (css = "tr td:nth-child(2)")
List<WebElement> productNames ; 

@FindBy (css =".totalRow button" )
WebElement checkoutBtn;


//	
//	driver.findElement(By.cssSelector(".totalRow button")).click();
//	
//	Actions action = new Actions(driver);
//	action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform() ;
//	
//	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section[class*='ta-results']")));
//	
	
	
	public boolean matchName(String productname) {
	return	productNames.stream().anyMatch(coat->coat.getText().equalsIgnoreCase(productname));
	}
	
	public PaymentPage checkout() {
		checkoutBtn.click();
		return new PaymentPage(driver);
		
	}
	
}
