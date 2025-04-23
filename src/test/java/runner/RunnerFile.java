package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions (
		          features = "src/test/resources/Features",
		          //features = "@rerun.txt",
		          glue = {"stepDefinitions"},
		          tags = "@Test",
		          monochrome = true,
		          dryRun = false,
		          plugin = {"pretty", "rerun:rerun.txt", "html:target/html-report.html",
		        		  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		        		  "html:target/cucumber-reports/cucumberreport.html", 		        		  
		        		  }
		         )



public class RunnerFile
{
	
}


