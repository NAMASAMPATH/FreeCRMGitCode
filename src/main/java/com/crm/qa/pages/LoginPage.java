package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//*[@id=\"navbar-collapse\"]/ul/li[2]/a")
	WebElement signUp;
	
	@FindBy(xpath="//*[@id=\"loginForm\"]/div/div/input")
	WebElement loginbtn;
	
	@FindBy(xpath="//img[contains(@src,'freecrm.jpg')]")
	WebElement logo;
	
	// Initializating the Page Objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateLoginPageLogo()
	{
		return logo.isDisplayed();
	}
	
	public HomePage login(String uname,String pwd) throws InterruptedException
	{
		username.sendKeys(uname);
		password.sendKeys(pwd);
		Thread.sleep(3000);
		loginbtn.click();
		
		// since once we click login it will go to home page(Landing Page)
		return new HomePage();
	}
}
