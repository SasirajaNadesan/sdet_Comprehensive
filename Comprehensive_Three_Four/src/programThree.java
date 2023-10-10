import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class programThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\softwares\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

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

}
