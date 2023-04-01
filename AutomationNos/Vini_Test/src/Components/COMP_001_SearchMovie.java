package Components;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Util.GenericMethods;

public class COMP_001_SearchMovie {
	private GenericMethods genericMethods = new GenericMethods();

	@Test
	public void Search(WebDriver driver,String path, String movieName) throws InterruptedException {

		// Fill the "Search" Field
		System.out.println("----COMP_001_SearchMovie----");
		WebElement searchBar = driver.findElement(By.xpath("//input[@class]"));
		searchBar.sendKeys(movieName + Keys.ENTER);
		System.out.println("Search: " + movieName);

		// Wait 3s
		Thread.sleep(3000);
		
		//Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on the movie
		List<WebElement> option = driver
				.findElements(By.xpath("//div[@role='listbox']//span[contains(.,'" + movieName + "')]"));
		if (option.size() > 0) {
			for (int i = 0; i <= option.size(); i++) {
				if (option.get(i).getText().equals(movieName)) {
					option.get(i).click();
					break;
				}
			}
		} else {
			option.get(0).click();
		}

		// Wait 3s
		Thread.sleep(3000);
		
		//Take Screenshot
		GenericMethods.takeScreenshot(path);

		String ActualMessage = driver.findElement(By.xpath("//h2[contains(.,'" + movieName + "')]")).getText();

		System.out.println("expectedMessage: " + movieName);
		System.out.println("ActualMessage: " + ActualMessage);
		Assert.assertEquals("Message: ", movieName, ActualMessage);

		// Wait 3s
		Thread.sleep(3000);

	}

}
