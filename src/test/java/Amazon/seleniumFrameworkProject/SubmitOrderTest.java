package Amazon.seleniumFrameworkProject;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Amazon.seleniumFrameworkProject.TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatelog;

public class SubmitOrderTest extends BaseTest {
	public String productname = "QWERTY";
	
		@Test(dataProvider ="getData",groups="purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {
		
		
		
		LandingPage landingPage = launchApplication();
		ProductCatelog catelog  = 	landingPage.loginApplication(input.get("email"), input.get("password"));
			
			
					catelog.getProductList();
					catelog.addProducttoCart(input.get("productName"));
					catelog.clickOnAddToCart();
				CheckOutPage checkOutPage = 	catelog.clickOncheckoutBtn();
					
					
				Boolean checkMatch = 	checkOutPage.matchName(input.get("productName"));
				Assert.assertTrue(checkMatch);
					PaymentPage paymentPage = checkOutPage.checkout();
					paymentPage.selectCountrymethod("india");
				ConfirmationPage confirmationPage = 	paymentPage.submitOrder();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			String getmsg = 	confirmationPage.getConfirmMsg();
			
			Assert.assertTrue( getmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
					
	}

	

@Test(dependsOnMethods = "submitOrder")
public void OrderHistoryTest() {
	
	ProductCatelog productCatelog = landingPage.loginApplication("a10@gmail.com", "Pass@1234");
	OrderPage orderPage = productCatelog.gotoOrdersPage();
	Assert.assertTrue(orderPage.verifyOrderDisplay(productname));
	
}

@DataProvider
public List<HashMap<String, String>> getData() throws IOException{
	
//	HashMap<String, String> hashMap = new HashMap<String, String>();
//	hashMap.put("email","a10@gmail.com");
//	hashMap.put("password", "Pass@1234");
//	hashMap.put("productName",  "QWERTY") ;
//	
//	HashMap<String, String> hashMap2 = new HashMap<String, String>();
//	hashMap2.put("email","a110@gmail.com");
//	hashMap2.put("password", "Pass@1234");
//	hashMap2.put("productName",  "ADIDAS ORIGINAL") ;
//	
//	
//	return new Object[][] {{hashMap},{hashMap2}};
	
	List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\nvc37928\\Downloads\\Selnium_Interview_2024\\Selnium_Interview_2024\\seleniumFrameworkProject\\src\\test\\java\\Amazon\\seleniumFrameworkProject\\Data\\purchaseOrder.json") ;
	return data ;
	
} 
	
}


	

