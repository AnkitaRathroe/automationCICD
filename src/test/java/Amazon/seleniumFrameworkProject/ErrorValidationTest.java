package Amazon.seleniumFrameworkProject;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Amazon.seleniumFrameworkProject.TestComponent.BaseTest;
import Amazon.seleniumFrameworkProject.TestComponent.Retry;
import pageObjects.CheckOutPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatelog;

public class ErrorValidationTest extends BaseTest {
	
	

		@Test(groups={"ErrorHandling"},retryAnalyzer =Retry.class)
	public void LoginErrorValidations() throws IOException {
		
		String name = "QWERTY";
		
		LandingPage landingPage = launchApplication();
		 	landingPage.loginApplication("a110@gmail.com", "Pass@12345");
		 	System.out.println("error msgis " + landingPage.getErrorMsg());
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMsg());
			
		}
		
		
		//negative scenario test ,with wrong email password
		public void productErrorValidationTest() throws IOException {
			
			String name = "QWERTY";
			
			LandingPage landingPage = launchApplication();
			ProductCatelog catelog  = 	landingPage.loginApplication("a10@gmail.com", "Pass@1234");
				
				
						catelog.getProductList();
						catelog.addProducttoCart(name);
						catelog.clickOnAddToCart();
					CheckOutPage checkOutPage = 	catelog.clickOncheckoutBtn();
						
						
					Boolean checkMatch = 	checkOutPage.matchName("QWERTY 33");
					Assert.assertFalse( checkMatch);
		}


		

		
		
		

}

	

