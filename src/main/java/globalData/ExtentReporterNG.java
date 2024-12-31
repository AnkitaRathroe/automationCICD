package globalData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public   class ExtentReporterNG {
	
	static ExtentReports extent ;
	
	public  static ExtentReports config() {
		String path = System.getProperty("user.dir") +"\\reports\\index.html" ;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path) ;
		reporter.config().setReportName("webAutomationreport");
		reporter.config().setDocumentTitle("test results");
		
		 extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ankita Rathore");
		return extent ;
	}
	

}
