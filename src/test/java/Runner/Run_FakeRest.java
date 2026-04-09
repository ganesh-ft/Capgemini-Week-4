package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Feature/kill.feature",   
        glue = "StepDefinition",              
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)


public class Run_FakeRest extends AbstractTestNGCucumberTests {

}
