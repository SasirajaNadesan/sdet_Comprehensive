package comprehensive_six;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComprehensiveSix {
	
	@Test
	public static void readExcelData() {
		try {
		String excelpath = "./Data/ReaDWriteData.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(excelpath);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("no of rows"+rowCount);
		
		// Using the Iterator to read data
		java.util.Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();

			java.util.Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;
				default:
					break;
				}
				System.out.print(" | ");
			}
			System.out.println();
		}
		workbook.close();
		}
		catch(Exception e){
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
	}
	//Tesing the UI Automation
	
	@Test
	public static void uiLinkVerification() {
		// Defining the chrome driver & URL
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.automationanywhere.com/");

		// checking the element Displayed or not
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='coh-row-inner coh-ce-cpt_aai_site_header-c6638c7f']/div[1]"))
						.isDisplayed());
		boolean display = driver
				.findElement(By.xpath("//div[@class='coh-row-inner coh-ce-cpt_aai_site_header-c6638c7f']/div[1]"))
				.isDisplayed();
		System.out.println("Element is Displayed :" + display);

		// checking the cookies button displayed if so click that else checking on the
		// request demo button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement cookiesBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
		if (cookiesBtn.isDisplayed()) {
			cookiesBtn.click();
			System.out.println("cookiesBtn Displayed & clicked");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// To perform the scrool on website using selenium to enbale the request demo
		// button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");

		
		System.out.println("Element is Displayed :"
				+ driver.findElement(By.xpath("(//div[@class='coh-container utility-navbtn'])[1]")).isDisplayed());
		System.out.println("Element is clickabale :"
				+ driver.findElement(By.xpath("(//div[@class='coh-container utility-navbtn'])[1]")).isEnabled());
		driver.quit();

	}
	
	@Test
	public static void seleniumUILinksVerification() {

		// Defining the chrome driver & URL
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.automationanywhere.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		WebElement cookiesBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
		if (cookiesBtn.isDisplayed()) {
			cookiesBtn.click();
		}

		// Passing the expected elements to a String array list
		ArrayList<String> expectedElements = new ArrayList<String>();
		expectedElements.add("Products");
		expectedElements.add("Solutions");
		expectedElements.add("Resources");
		expectedElements.add("Beyond RPA");
		expectedElements.add("Company");

		// Passing the List of actual items to a Webelement list
		List<WebElement> allElements = driver.findElements(By.xpath(
				"//ul[@class='coh" + "-menu-list-container coh-unordered-list menu-level-1 coh-ce-646fa54d']/li/a"));
		for (WebElement element : allElements) {
			String actual = element.getText();
			if (expectedElements.contains(actual)) {
				System.out.println(actual + " conatains in " + expectedElements);
			}

		}

		// Verify all the links are redirected
		for (String element : expectedElements) {
			linktoClick(element, driver);

		}
		driver.quit();
	}

	public static void linktoClick(String urlToClick, WebDriver driver) {
		driver.findElement(By.xpath("//a[normalize-space()='" + urlToClick + "']")).click();
		String urlPostClick = driver.getCurrentUrl();
		System.out.println(urlToClick + " Link is clicked");
		System.out.println(urlPostClick);

	}

	
}
