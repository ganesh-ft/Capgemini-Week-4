package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Feature/data.feature",   
        glue = "StepDefinition.data_tab",              
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)

public class data_runner extends AbstractTestNGCucumberTests{

}
