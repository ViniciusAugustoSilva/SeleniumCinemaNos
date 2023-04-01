package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.accessibility.AccessibleValue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class GenericMethods {

	// Function to read a Json File
	public JSONObject readFromJsonFile(String jsonName) throws IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(".\\jsonFile\\" + jsonName);
		Object obj = jsonParser.parse(reader);
		JSONObject JsonObj = (JSONObject) obj;

		return JsonObj;

	}

	// Function to Create Report
	public static void createReport(WebDriver driver, String path) {
		try {

			// Create folder/directory if not exist.
			File file = new File(path);
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create evidence Path
	public static String docPath(String scenarioName) {
		String dirPath;
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);

		dirPath = "./Evidences/" + scenarioName + "_" + date1 + "/";

		return dirPath;

	}

	// Teke Screenshot
	public static void takeScreenshot(String path) {
		try {
			String screenshot_name = System.currentTimeMillis() + ".png";
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			File file = new File(path + screenshot_name);
			ImageIO.write(image, "png", file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create evidence folder
	public static void createEvidenceDoc(String path, String scenarioName) {
		try {
			File file = new File(path);
			String[] pictures = file.list();

			XWPFDocument docx = new XWPFDocument();
			XWPFRun run = docx.createParagraph().createRun();
			FileOutputStream out = new FileOutputStream(path + scenarioName + ".docx");

			for (int i = 0; i < pictures.length; i++) {
				InputStream pic = new FileInputStream(path + pictures[i]);
				run.addBreak();
				run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, pictures[i], Units.toEMU(500), Units.toEMU(350));
				pic.close();

			}

			docx.write(out);
			out.flush();
			out.close();
			docx.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
