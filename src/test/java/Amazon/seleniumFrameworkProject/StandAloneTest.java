package Amazon.seleniumFrameworkProject;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class StandAloneTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		String name = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("a10@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Pass@1234");
		driver.findElement(By.id("login")).click();
		LandingPage landingpage = new LandingPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> productList= 	driver.findElements(By.cssSelector(".mb-3"));
	
WebElement zara =	productList.stream().
filter(product->product.findElement(By.cssSelector("b")).getText().
		equals("ZARA COAT 3"))
.findFirst()
.orElse(null);

zara.findElement(By.cssSelector(".card-body button:last-of-type")).click();

	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
	boolean booleanResult = cartProduct.stream().anyMatch(prod-> prod.getText().equalsIgnoreCase(name));
	
	Assert.assertEquals(true, booleanResult);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions action = new Actions(driver);
	action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform() ;
	
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section[class*='ta-results']")));
	
	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
Thread.sleep(2000);
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String getName = driver.findElement(By.cssSelector(".hero-primary")).getText();
	System.out.println(getName);
	Assert.assertTrue( getName.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	}

}
