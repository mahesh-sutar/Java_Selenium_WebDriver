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

public class MouseHoverAction {

	WebDriver driver;

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\git_workspace\\selenium_webdriver\\selenium_webdriver_concepts\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.goindigo.in/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void mouseHovertest() {

		WebElement bookLink = driver.findElement(By.xpath("//a[@title='Book']"));

		// To mouse hover : Use Actions class
		Actions actions = new Actions(driver);

		actions.moveToElement(bookLink).build().perform();

		// Click on link located inside book
		driver.findElement(By.xpath("//*[@class='flight']"));

		Assert.assertEquals(driver.getTitle(), "Online Flight Booking for Domestic & International Destinations | IndiGo");

	}

	@AfterMethod
	public void close() {
		driver.close();
	}

}
