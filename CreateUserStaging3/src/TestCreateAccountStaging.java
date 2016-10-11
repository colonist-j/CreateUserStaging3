import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestCreateAccountStaging {
	//RandomStringEmailStaging randomEmail= new RandomStringEmailStaging();
	String nameEmail=RandomStringEmailStaging.generateRandomString();

	
	private static Logger Log = Logger.getLogger(LogLog4jStaging.class.getName());

	/*File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");// ("C:\\Users\\ilya.z\\AppData\\Local\\Mozilla
																						// Firefox\\firefox.exe");
	FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
	FirefoxProfile firefoxProfile = new FirefoxProfile();
	WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);*/
	

	@Test
	public void testCreate() throws InterruptedException {
		//Logger.getRootLogger().setLevel(Level.OFF);
		BasicConfigurator.configure();
		
		DesiredCapabilities DesireCaps = new DesiredCapabilities();
		DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\ilya.z\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		WebDriver driver = new PhantomJSDriver(DesireCaps);
		 
		driver.manage().window().maximize();
		driver.get("https://dev-staging.hedgestonegroup.com/");
		Log.info("hedgestonegroup site opened");
		WebElement openAccountButton = driver.findElement(By.xpath("//div[@class=\"btnContainer\"]//span[text()=\"Open an account\"]"));
		openAccountButton.click();
		Log.info("create account page opened");
		driver.findElement(By.name("FirstName")).clear();
		driver.findElement(By.name("FirstName")).sendKeys("test" + nameEmail); //generateRandomString());
		Log.info("firstName is inserted: "+nameEmail);
		driver.findElement(By.name("LastName")).clear();
		driver.findElement(By.name("LastName")).sendKeys("testautoo");
		Log.info("secondName is inserted");
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(nameEmail + "@yopmail.com");
		Log.info("email is inserted: "+ nameEmail + "@yopmail.com");
		driver.findElement(By.name("phone")).clear();
		driver.findElement(By.name("phone")).sendKeys("492180185611");
		Log.info("phone is inserted");
		new Select(driver.findElement(By.name("Year"))).selectByVisibleText("1981");
		Log.info("year is selected");
		new Select(driver.findElement(By.name("Month"))).selectByVisibleText("12 - December");
		Log.info("month is selected");
		Thread.sleep(5000);
		WebElement dayElement = driver.findElement(By.name("Day"));
		new Select(dayElement).selectByIndex(1);
		Log.info("day is selected");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("123456");
		Log.info("password is inserted");
		driver.findElement(By.name("ConfirmPassword")).clear();
		driver.findElement(By.name("ConfirmPassword")).sendKeys("123456");
		Log.info("password is confirmed");
		driver.findElement(By.name("terms")).click();
		Log.info("terms is checked");
		driver.findElement(By.cssSelector("button.btn.submitBtn")).click();
		Log.info("button is clicked");
		System.out.println("yep");
		Thread.sleep(20000);
		Assert.assertEquals("https://dev-staging.hedgestonegroup.com/trade", driver.getCurrentUrl());
		Log.info("Trade page is opened");
		driver.close();
		driver.quit();
		Log.info("browser closed");

	}
}
