package flipkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportTest
{
	static  ExtentHtmlReporter hhh;
	static ExtentReports ex;
private static 	WebDriver driver;
static ExtentTest t1;

	@BeforeSuite
	public static void setUp()
	{
		
		hhh=new ExtentHtmlReporter("./reports/learn3_automation.html");
		//create extend report and attach reporter
		ex=new ExtentReports();
		ex.attachReporter(hhh);
	}

	@BeforeTest
	public void setTest()
	{
		String path=System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver",path+ "\\drivers\\firefoxdriver\\geckodriver.exe");
		driver=new FirefoxDriver();
	}

	@Test(dataProvider = "test11")
	public void test1(String username,String password) throws Exception
	{
		System.out.println(username+" "+password);

		 t1=ex.createTest("1st Test","Sample Description");

		t1.log(Status.INFO, "This step shows usages of log(status,details)");
		t1.info("Step goes to flipcart login");

		driver.get("https://www.flipkart.com/");

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")).sendKeys(username);

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")).sendKeys(password);

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
		Thread.sleep(2000);
		//log with snapshot
		t1.pass("details",MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());

		//test with snapshot
		t1.addScreenCaptureFromPath("screenshot.png");

	}

	@DataProvider(name= "test11")

	public  Object[][] getData()
	{
		String excelPath="C:\\Users\\DELL\\eclipse-workspace\\SF1\\excel\\data.xlsx";
		Object data[][]= testData(excelPath,"Sheet1");
		return data;
	}


	public Object[][] testData(String excelPath, String sheetName)
	{
		ExcelUtils excel=new ExcelUtils(excelPath, sheetName);
		int rowCount=	excel.getRowCount();
		int colCount=excel.getColCount();

		Object data[][]=new Object[rowCount-1][colCount];

		for(int i=1;i<rowCount;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				String cellData=excel.getCellDataString(i, j);
				//System.out.println(cellData+" ");
				data[i-1][j]=cellData;
			}
		}
		return data;
	}

	
	@AfterSuite
	public void afterMethod()
	{
		driver.close();
		driver.quit();
		System.out.println("Test Successful");
	}



	@AfterTest
	public void tearDown()
	{   //ex.endTest(t1);
		ex.flush();
	}
	


}
