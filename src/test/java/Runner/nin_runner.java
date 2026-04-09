package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Feature/mad.feature",   
        glue = "StepDefinition.ninzaaa_emp",              
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)

public class nin_runner extends AbstractTestNGCucumberTests {
}