package com.Framework.Automation;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class  AppTest{
	public static String path = "D:\\Users\\XY59005\\Desktop\\Drivers\\chromedriver.exe";
	public static WebDriver driver;
	public static SalesDashboard sd = new SalesDashboard();
	
	@Test 
	
	public void Login() {
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.navigate().to("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		
	}
	
	@Test (dependsOnMethods = {"Login"})
	public void shop() throws InterruptedException {
		String actualtab = driver.getWindowHandle();
		System.out.println("Tab 1 is : "+ "  " +  actualtab);
		driver.findElement(By.xpath(sd.burgerMenu)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(sd.mobilesAndComputers)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(sd.screenProtectors)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(sd.samsung)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(sd.m21)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(sd.product1)).click();
		Thread.sleep(2000);
		String tabNow = driver.getWindowHandle();
		System.out.println("Tab Now : "+tabNow);
		
		Reporter.log("Successfully opened tab 2 ");
		
		
		ArrayList <String> AllTab = new ArrayList <String>( driver.getWindowHandles());
		String tab2 = AllTab.get(1);
		System.out.println(tab2);
		driver.switchTo().window(tab2);
		//System.out.println(AllTab);
		
		//WebDriver newTab=driver.switchTo().newWindow(WindowType.TAB);
		
		String myTab = driver.getWindowHandle();
		System.out.println("Tab 2 is : "+ "  " +  myTab);
		
		
		String title =  driver.getTitle();
		System.out.println(title);
		
		driver.findElement(By.xpath("//a[@data-csa-c-content-id='nav_cs_electronics']")).click();
		Thread.sleep(10000);
		try {
		driver.findElement(By.xpath(SalesDashboard.addToCart));
		Thread.sleep(2500);
		String realMsg = driver.findElement(By.xpath(SalesDashboard.actualMsg)).getText();
		Thread.sleep(2500);
		Assert.assertEquals(sd.actualMsg, realMsg);
		}catch (Exception e) {System.out.println("An exception occured ! ");}
		Reporter.log("Test passed successfully ");
		
		driver.quit();
		
		
	}
	
	
}