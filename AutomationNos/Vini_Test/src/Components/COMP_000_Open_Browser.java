package Components;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Util.GenericMethods;

public class COMP_000_Open_Browser {
	private GenericMethods genericMethods = new GenericMethods();

	@Test
	public void Open_Chrome(WebDriver driver, String path, String url) throws InterruptedException {
		File f = new File("chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");

		System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		driver.manage().window().maximize();
		driver.get(url);

		// Wait page loads
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

		// Wait 3s
		Thread.sleep(3000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Check if "Aceitar todos os cookies" is Enabled
		if (driver.findElements(By.xpath("//a[contains(.,'Aceitar todos os cookies')]")).size() > 0) {
			driver.findElement(By.xpath("//a[contains(.,'Aceitar todos os cookies')]")).click();
		}

		// Wait 1s
		Thread.sleep(1000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		String expectedMessage = "Cinemas";
		String ActualMessage = driver.findElement(By.xpath("//div[@class='root-header__title']")).getText();

		System.out.println("expectedMessage: " + expectedMessage);
		System.out.println("ActualMessage: " + ActualMessage);
		Assert.assertEquals("Message: ", expectedMessage, ActualMessage);

	}

}
