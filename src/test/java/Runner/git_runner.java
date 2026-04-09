package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "./src/test/java/Feature/git.feature",     
        glue = "StepDefinition.git_hub",              
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class git_runner extends AbstractTestNGCucumberTests {

}