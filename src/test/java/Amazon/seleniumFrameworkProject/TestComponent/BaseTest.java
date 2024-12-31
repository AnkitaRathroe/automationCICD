package Amazon.seleniumFrameworkProject.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;


public class BaseTest {

	public  WebDriver driver ;
	public LandingPage landingPage ;
	public WebDriver  initisliseDriver() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\globalData\\global.properties");

			prop.load(inputStream);
		
		
		//String browser = prop.getProperty("browser");
		String browser =	System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://rahulshettyacademy.com/client");
			
		}else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\nvc37928\\Downloads\\Fireforx geckdriver\\geckodriver-v0.33.0-win-aarch64\\geckofriver.exe");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize(); 
		return driver ;
		
	}
	
	public String getScreenShot(String testCse, WebDriver driver ) throws IOException {
		
	TakesScreenshot screeenshot	=(TakesScreenshot)driver;
File source = 	screeenshot.getScreenshotAs(OutputType.FILE);

File file = new File(System.getProperty("user.dir") + "//reports" + testCse +".png" );
FileUtils.copyFile(source, file);
return System.getProperty("user.dir") + "//reports" + testCse +".png"  ;
	}
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//reading json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath));
	
	//String to HashMap -jackson dataBind 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>>data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data  ;
		
		
	}


	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
	 driver = 	initisliseDriver();
	 LandingPage landingPage = new LandingPage(driver);
	//	landingPage.gotopage();
		return landingPage;
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// TODO Auto-generated method stub

		driver.close();
	}

}
