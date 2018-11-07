package com.crm.qa.base;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	

//	private static EventFiringWebDriver e_driver;
//	private static WebEventListener eventListener;
//	private static WebDriverWait wait;
//	public static int waitTime = 10;
	
	
	public TestBase()
	{
		try {
			prop=new Properties();
			FileInputStream fis =new FileInputStream("/var/lib/jenkins/workspace/FreeCRM/src/main/java/com/crm/qa/config/config.properties");
			prop.load(fis);
			
		} catch (Exception e) {
			
		}
	}
	public static void initialization() throws MalformedURLException
	{
		String browser=prop.getProperty("browser");
		if(browser.equals("chrome"))
		{
			/*System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();*/
			DesiredCapabilities cap=DesiredCapabilities.chrome();
			cap.setCapability("version", "69.0.3497.100");
			cap.setCapability("platform", "LINUX");
			driver=new RemoteWebDriver(new URL("http://35.165.119.163:4446/wd/hub"), cap);
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\geckodriver\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\JavaProjects\\iedriver.exe");
			driver=new FirefoxDriver();
		}
		
//		wait = new WebDriverWait(driver, waitTime);
//
//		// Initializing EventFiringWebDriver using Firefox WebDriver instance
//		e_driver = new EventFiringWebDriver(driver);
//
//		// Now create object of EventListerHandler to register it with EventFiringWebDriver
//		eventListener = new WebEventListener();
//
//		e_driver.register(eventListener);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
	}

}
