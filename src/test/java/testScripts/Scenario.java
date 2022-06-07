package testScripts;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import MachineTest.Selenium.Base;

public class Scenario extends Base {

	@Test
	public void validateitem() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\priya\\pwcinterview\\Selenium\\src\\main\\java\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(geturl());
		driver.findElement(By.xpath("//div[@id='nav-xshop']//a[normalize-space()='Mobiles']")).click();
		List<WebElement> brands = driver
				.findElements(By.xpath("//span[normalize-space()='Brands']//parent::div//following-sibling::ul//li"));
		WebElement checkbox = driver.findElement(
				By.xpath("//span[normalize-space()='Brands']//parent::div//following-sibling::ul//li[4]//i"));
		for (WebElement b : brands) {
			if (b.getText().equalsIgnoreCase(getbrand())) {
				checkbox.click();
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("(//h2/a)[1]")).click();
		String parentwindow=driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		while(it.hasNext()) {
			String childwindow=it.next();
			if(!parentwindow.equals(childwindow)) {
				driver.switchTo().window(childwindow);
			}
		}
		
		String[] title = driver.findElement(By.id("productTitle")).getText().split("\\(");
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		driver.findElement(By.xpath("//span[@class='a-button a-button-base attach-button-large attach-cart-button']"))
				.click();
		String[] cartitem = driver.findElement(By.xpath("//span[@class='a-truncate-cut']")).getText().split("\\(");
		Assert.assertEquals(title[0], cartitem[0]);
		driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
		String loginerror = driver.findElement(By.xpath("//h1[@class='a-spacing-small']")).getText();
		Assert.assertEquals(loginerror,"Sign-In");
	}

}
