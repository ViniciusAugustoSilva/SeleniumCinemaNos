package Cenario;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Components.COMP_001_SearchMovie;
import Components.COMP_002_BuyTicket_InvalidTelemovel;
import Components.COMP_000_Open_Browser;
import Util.GenericMethods;

public class SCN_001_TryToBuyTicket_InvalidTelemovel {

	private WebDriver driver = new ChromeDriver();
	private COMP_000_Open_Browser COMP_000_openChrome = new COMP_000_Open_Browser();
	private COMP_001_SearchMovie COMP_001_addCustomer = new COMP_001_SearchMovie();
	private COMP_002_BuyTicket_InvalidTelemovel COMP_002_buyTicket_InvalidTelemovel = new COMP_002_BuyTicket_InvalidTelemovel();
	private GenericMethods genericMethods = new GenericMethods();
	public String evidencepath = GenericMethods.docPath("SCN_001_TryToBuyTicket_InvalidTelemovel");
	

	@Before
	public void OpenBrowser() throws IOException, ParseException, InterruptedException {
		
		// Read Json
		JSONObject JSONOBJ = genericMethods.readFromJsonFile("Desafio1.json");
		
		//Create file evidence
		GenericMethods.createReport(driver, evidencepath);

		// OPEN BROWSER
		COMP_000_openChrome.Open_Chrome(driver,evidencepath, (String) JSONOBJ.get("URL"));
		
	}

	@Test
	public void AddCustomer() throws InterruptedException, IOException, ParseException {
		
		// Read Json
		JSONObject JSONOBJ = genericMethods.readFromJsonFile("Desafio1.json");

		// Search Movie
		COMP_001_addCustomer.Search(driver,evidencepath, 
				(String) JSONOBJ.get("MOVIENAME"));
		
		// Buy Ticket
		COMP_002_buyTicket_InvalidTelemovel.buyTicket(driver,evidencepath, (String) JSONOBJ.get("ZONA"),
					(String) JSONOBJ.get("QNT"),
					(String) JSONOBJ.get("NOME"),
					(String) JSONOBJ.get("TELEMOVEL"),
					(String) JSONOBJ.get("EMAIL"));
	}
	

	@After
	public void Close() {
		
		//Create file evidence
		GenericMethods.createEvidenceDoc(evidencepath, "SCN_001_TryToBuyTicket_InvalidTelemovel");
		
		driver.close();
	}

}
