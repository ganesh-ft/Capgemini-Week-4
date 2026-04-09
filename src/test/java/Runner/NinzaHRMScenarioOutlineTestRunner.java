package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Feature/life.feature",
        glue = "StepDefinition.ninzaa",
        plugin = {
                "pretty",
                "html:target/NinzaHrmScenarioOutlinereport.html"
        },
	    monochrome = true
	)
public class NinzaHRMScenarioOutlineTestRunner extends AbstractTestNGCucumberTests {
	
}