package com.qa.selenium.concepts.multiple_windows;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleWindowsHandle {

	WebDriver driver;

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\git_workspace\\selenium_webdriver\\selenium_webdriver_concepts\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testHandleMultipleWindow() {
		String parent_window = driver.getWindowHandle();

		String parent_window_title = driver.getTitle();
		System.out.println("Parent window title is : " + parent_window_title);

		//Link is inside frame, need to switch ro frame
		driver.switchTo().frame("iframeResult");
		
		// Click on the link that opens in new tab
		driver.findElement(By.xpath("//a[text()='Visit W3Schools.com!']")).click();

		// Find all opened windows
		Set<String> allWindows = driver.getWindowHandles();

		// Iterate through Set
		Iterator<String> iterator = allWindows.iterator();

		while (iterator.hasNext()) {
			String child_window = iterator.next();

			if (!parent_window.equalsIgnoreCase(child_window)) {
				driver.switchTo().window(child_window);

			}
		}
		
		String child_window_title = driver.getTitle();

		System.out.println("Child window title is : " + child_window_title);
		Assert.assertEquals(child_window_title, "W3Schools Online Web Tutorials");

		
		// Switching back to parent window
		driver.switchTo().defaultContent();
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}
