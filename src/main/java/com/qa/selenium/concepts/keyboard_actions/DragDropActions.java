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

//This demo will explains concept of actions class 

public class DragDropActions {

	WebDriver driver;

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\git_workspace\\selenium_webdriver\\selenium_webdriver_concepts\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void dragDropTest() {

		WebElement frameLocator = driver.findElement(By.xpath("//*[@id='content']/iframe"));
		// As WebELements are under IFrame, we need to switch to frame first
		driver.switchTo().frame(frameLocator);

		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));

		// Create object of Actions class and webdriver reference to it
		Actions actions = new Actions(driver);

		// Drag and drop using actions
		actions.clickAndHold(source).moveToElement(target).release().build().perform();

		Assert.assertEquals(target.getText(), "Dropped!");

		// Switch back to parent window
		driver.switchTo().defaultContent();
	}

	@AfterMethod
	public void close() {
		driver.close();
	}
}
