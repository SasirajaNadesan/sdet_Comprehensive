import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class programFour {

	public static void main(String[] args) {
		// defining the Driver path
		System.setProperty("webdriver.chrome.driver",
				"C:\\softwares\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

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
		
		//Assertions
		System.out.println(urlToClick + " Link is clicked");
		System.out.println(urlPostClick);

	}
}
