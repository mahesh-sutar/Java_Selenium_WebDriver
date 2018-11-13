package com.qa.selenium.concepts.keyboard_actions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RightClickOnElement {

	WebDriver driver;

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\git_workspace\\selenium_webdriver\\selenium_webdriver_concepts\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://labs.abeautifulsite.net/archived/jquery-rightClick/demo/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testRightClickAction() {

		WebElement targetElement1 = driver.findElement(By.xpath("//*[@id='div_1']"));

		// Create object of Actions class and pass the reference of driver to it
		Actions actions = new Actions(driver);

		actions.contextClick(targetElement1).build().perform();

		// Assert the test appeared inside input box after right click
		WebElement log1 = driver.findElement(By.xpath("//*[@id='log']"));
		Assert.assertEquals(log1.getText().contains("Click on div_1"), true);

	}

	@AfterMethod
	public void close() {
		driver.close();
	}

}
