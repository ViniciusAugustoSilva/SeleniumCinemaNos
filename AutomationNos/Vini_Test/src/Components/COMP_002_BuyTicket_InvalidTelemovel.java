package Components;

import static org.junit.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import Util.GenericMethods;
import net.bytebuddy.utility.RandomString;

public class COMP_002_BuyTicket_InvalidTelemovel {
	private GenericMethods genericMethods = new GenericMethods();

	@SuppressWarnings("deprecation")
	@Test
	public void buyTicket(WebDriver driver, String path, String zona, String qnt, String name, String telemovel,
			String email) throws InterruptedException {

		// ImplicitlyWait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on "Ver sessões"
		System.out.println("----COMP_002_BuyTicket----");
		driver.findElement(By.xpath("//span[@class='cmp_button-text']")).click();

		// Click on "Amanhã" button
		driver.findElement(By.xpath("//button[contains(.,'Amanhã')]")).click();

		// Select Zone
		Select switchZone = new Select(driver.findElement(By.id("search-region")));
		switchZone.selectByVisibleText(zona);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Wait 2s
		Thread.sleep(2000);

		// Click on the first movie session
		driver.findElement(By.xpath(
				"//div[@class='movie-detail__sessions-list__container__theater']//button[@data-sessiondate='Amanhã']"))
				.click();

		// Wait 1s
		Thread.sleep(1000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on 'Comprar bilhete' button
		driver.findElement(By.xpath("//span[contains(.,'Comprar bilhete')]")).click();

		// Wait 5s
		Thread.sleep(10000);

		// Click on quantity button
		driver.findElement(By.xpath("//span[@class='color--gray-300'][contains(.,'" + qnt + "')]")).click();

		// Take Screenshot
		Thread.sleep(1000);
		GenericMethods.takeScreenshot(path);

		// Click on 'Continuar' button
		driver.findElement(By.xpath("//button[contains(.,'Continuar')]")).click();

		// Wait 5s
		Thread.sleep(10000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on 'Continuar' button
		driver.findElement(By.xpath("//button[contains(.,'Continuar')]")).click();

		// Wait 6s
		Thread.sleep(10000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on 'Continuar' button
		driver.findElement(By.xpath("//button[contains(.,'Continuar')]")).click();

		// Wait 7s
		Thread.sleep(10000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on 'Continuar' button
		driver.findElement(By.xpath("//button[contains(.,'Continuar')]")).click();

		// Wait 5s
		Thread.sleep(10000);

		// Fill the Name field
		driver.findElement(By.xpath("//*[@id='b16-Input_Name']")).sendKeys(name);

		// Fill the Telemovel field
		driver.findElement(By.xpath("//*[@id='b16-Input_PhoneNumber']")).sendKeys(telemovel);

		// Fill the Email field
		driver.findElement(By.xpath("//*[@id='b16-Input_Email']")).sendKeys(email);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on "Finalizar" button
		driver.findElement(By.xpath("//button[contains(.,'Finalizar')]")).click();

		// Wait 5s
		Thread.sleep(10000);

		// Click on "MB WAY" field
		driver.findElement(By.xpath("//span[contains(.,'MB WAY')]")).click();

		//Fill "Telemovel MB WAY" field
		driver.findElement(By.id("phoneNumberId")).click();
		driver.findElement(By.id("phoneNumberId")).sendKeys(telemovel);

		// Wait 1s
		Thread.sleep(1000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Click on "Pagar agora" button
		driver.findElement(By.id("mbwayBtnId")).click();

		// Wait 5s
		Thread.sleep(10000);

		// Take Screenshot
		GenericMethods.takeScreenshot(path);

		// Validate Message
		String expectedMessage = "O pagamento não foi concluído.";
		String ActualMessage = driver.findElement(By.xpath("//*[@id=\"b8-Content\"]//span")).getText();

		System.out.println("\n expectedMessage: " + expectedMessage);
		System.out.println("ActualMessage: " + ActualMessage);
		Assert.assertEquals("Message: ", expectedMessage, ActualMessage);

	}

}
