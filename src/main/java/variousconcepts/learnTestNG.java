package variousconcepts;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class learnTestNG {
	

	WebDriver driver;
	String browser;
	String url;
	//to run as firefox change string to "firefox"
	//Element list
	
	By USER_NAME_FIELD=By.xpath("//input[@id='username']");
	By PASSWORD_FIELD=By.xpath("//input[@id='password']");
	By SIGNIN_BUTTON_FIELD=By.xpath("//button[@name='login']");
	By DASHBOARDLOGO_FIELD=By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	By CUSTOMER_MENU_BUTTON_FIELD=By.xpath("//*[@id=\"side-menu\"]/li[3]/a");
	By ADD_CUSTOMER_BUTTON_FIELD= By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a");
	By CUSTOMER_NAME_FIELD=By.xpath("//input[@id='account']");
	
	//Test data
	String userName="Demo@techfios.com";
	String password="abc123";
	String dashboardHeader="dashboard";
	
	@BeforeClass
	public void readConfig(WebDriverWait wdFactory) {
		wdFactory = new WebDriverWait(driver, null, null, 0, 0);
		//InputStream //BufferedReader //Scanner //FileReader
		
		try {
			
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
		//	String url = prop.getProperty("url");
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
			
		}
		
	
	
	
	
	@BeforeMethod
	public void init () {
		
		if(browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver=new ChromeDriver();
			
			//to make it where you want firefox as solid browser 
			//change else to else if (browser.equalsignorecase("firefox") just like how we did on if for chrome
		}else {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
		    driver=new FirefoxDriver();
		
			
		}
	
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/billing/?ng=login/");
		
		
		
	}
	
	@Test
	public void loginTest() throws InterruptedException {
		loginTest();
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_FIELD).click();
		Assert.assertEquals(driver.findElement(DASHBOARDLOGO_FIELD).getText(),"dashboard", "dashboard not available");
		driver.findElement(CUSTOMER_MENU_BUTTON_FIELD).click();
		driver.findElement(ADD_CUSTOMER_BUTTON_FIELD).click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(ADD_CUSTOMER_BUTTON_FIELD).getText(),"customerpage","page not available");
		driver.findElement(CUSTOMER_NAME_FIELD).sendKeys("Bahubali2");
		
		
		
		
		//driver.findElement(DASHBOARDLOGO_FIELD).sendKeys("");
		
	}
	//@Test
	//public void addCustomer() throws InterruptedException {
	//	loginTest();
//		driver.findElement(CUSTOMER_MENU_BUTTON_FIELD).click();
//		driver.findElement(ADD_CUSTOMER_BUTTON_FIELD).click();
//		Thread.sleep(5000);
//		Assert.assertEquals(driver.findElement(ADD_CUSTOMER_BUTTON_FIELD).getText(),"customerpage","page not available");
//		driver.findElement(CUSTOMER_NAME_FIELD).sendKeys("Bahubali2");
//		
//		
//	}
	
	
	
//	@AfterMethod
//	public void tearDown() {
		
	}
		//driver.close();
		//driver.quit();
		
		
//	}

//}
