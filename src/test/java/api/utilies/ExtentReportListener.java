package api.utilies;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener implements ITestListener{

	ExtentSparkReporter htmlReporter; //User interface set-up of report
	ExtentReports reports; //common information
	ExtentTest test; //entries for test
	ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();
	
	public void configureReport() {
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "PetStoreAutomationReport"+timeStamp+".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//"+reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//Add system/environment info to report
		reports.setSystemInfo("Machine", "TestPetStore");
		reports.setSystemInfo("OS", "Windows11");
		reports.setSystemInfo("User Name", "Saurabh Kuntala" );
		
		//configuration of change look and feel of report
		htmlReporter.config().setDocumentTitle("Pet Store API Automation Testing Report");
		htmlReporter.config().setReportName("Pet Store Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	@Override
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("Execution started By invoking on start method");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution ended By invoking on finish method");
		reports.flush();
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of test method passed : "+result.getName());
		test = reports.createTest(result.getName());
		tlTest.set(test);
		test.log(Status.PASS, MarkupHelper.createLabel("Name of failed test case is "+result.getName(), ExtentColor.GREEN));
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of test method failed : "+result.getName());
		test = reports.createTest(result.getName()); //This will give ExtentTest that why used "test="
		tlTest.set(test); //Save test to threadlocal 
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of failed test case is "+result.getName(), ExtentColor.RED));
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Name of test method skipped : "+result.getName());
		test = reports.createTest(result.getName()); //This will give ExtentTest that why used "test="
		tlTest.set(test); //Save test to threadlocal 
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of skipped test case is "+result.getName(), ExtentColor.YELLOW));
	}
	
}
