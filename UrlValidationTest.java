package com.location.tests.urlValidation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UrlValidationTest {

	private WebDriver driver;

	public static void main(String[] args) {

		UrlValidationTest urlValidationTest = new UrlValidationTest();
		urlValidationTest.invokeBrowser("firefox");

		urlValidationTest.executeTest("https://www.neighborhoodscout.com/ma/real-estate", "$425,980",
				"/html/body/section[5]/div[2]/div[1]/div/h3/span");

		urlValidationTest.executeTest("https://www.neighborhoodscout.com/ca/real-estate", "$535,420",
				"/html/body/section[5]/div[2]/div[1]/div/h3/span");

		urlValidationTest.executeTest("https://www.neighborhoodscout.com/ny/bronx/real-estate", "$465,935",
				"//*[@id=\"data\"]/section[1]/div[2]/div/div/h3/span");

		urlValidationTest.closeBrowser();
	}

	/***
	 * Function to execute the test cases
	 */
	
	public void executeTest(String urlEndpoint, String expectedMedianValue, String xpath) {
		driver.get(urlEndpoint);

		WebElement medianvalueElement = driver.findElement(By.xpath(xpath));
		String medianvalue = medianvalueElement.getText();

		if (medianvalue.equals(expectedMedianValue)) {
			System.out.println("Value is found in URL : " + urlEndpoint);
		} else {
			System.out.println("Value not found in URL : " + urlEndpoint);
		}

	}

	/**
	 * Handles opening the respective browser based on the input browser name
	 * 
	 * @param browser
	 */
	public void invokeBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			// Firefox is the default
			System.setProperty("webdriver.gecko.driver",
					"C:\\selenium downloads\\geckodriver-v0.24.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void closeBrowser() {
		driver.quit();
	}

}
