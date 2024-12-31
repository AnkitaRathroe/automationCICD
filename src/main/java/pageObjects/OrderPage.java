package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import AbstractComponent.BaseClass;

public class OrderPage extends BaseClass {

	public WebDriver driver ;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver= driver ;
		
	}
	
	
@FindBy (css = ".cartSection h3")
List<WebElement> cartProducts ; 

@FindBy (css =".totalRow button" )
WebElement checkoutBtn;


	
	public boolean verifyOrderDisplay(String name) {
	return	cartProducts.stream().anyMatch(coat->coat.getText().equalsIgnoreCase(name));
	}
	
	
	



}
