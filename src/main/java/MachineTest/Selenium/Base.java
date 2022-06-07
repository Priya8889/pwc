package MachineTest.Selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Base {

	public Properties prop;
	public WebDriver driver;

	public String geturl() throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\priya\\pwcinterview\\Selenium\\src\\main\\java\\resources\\data.properties");
		prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("url");
		return url;
	}

	public String getbrand() {
		String brandname = prop.getProperty("brand");
		return brandname;

	}
}
