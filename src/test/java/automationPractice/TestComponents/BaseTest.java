package automationPractice.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import automationPractice.pageobject.Landingpage;

public class BaseTest {
	
	public WebDriver driver;
	public Landingpage l;

	public WebDriver initializeDriver() throws IOException
	{
		//Properties class
		Properties pro = new Properties();
		FileInputStream in=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//automationPractice//resourses//GlobalData.properties");
		
		pro.load(in);
		String browsername=System.getProperty("browser")!=null?System.getProperty("browser"):pro.getProperty("browser");
		
		if(browsername.contains("chrome"))
		{
		ChromeOptions option = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sweet\\Downloads\\chromedriver.exe");
		if(browsername.contains("headless"))
		{
		    option.addArguments("headless");
		}
		driver = new ChromeDriver(option);
	      driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\sweet\\Downloads\\geckodriver.exe");
			driver = new FirefoxDriver();
	}
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return driver;
	}
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read Json to String
		String jsoncontent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String to HasMap Jackson Databind
				ObjectMapper mapper=new ObjectMapper();
				List<HashMap<String,String>> data= mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
				return data;
	}
	public String getScreenshot(String testCaseName, WebDriver driver2) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir") + "//report//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//report//" + testCaseName + ".png";
		
	}
	@BeforeMethod(alwaysRun=true)
	public Landingpage launchApplication() throws IOException
	{
		driver = initializeDriver();
		l=new Landingpage(driver);
	    l.goTo();
	    return l;
	}
	@AfterMethod(alwaysRun=true)
	public void turndown()
	{
		driver.close();
	}
}
