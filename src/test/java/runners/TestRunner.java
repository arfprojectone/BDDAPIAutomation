package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path ke file feature
        glue = "stepDefinitions", // Path ke step definitions
        plugin = {"pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true // Output lebih rapi
//        tags = "@User" // Bisa disesuaikan dengan tag pada feature file

)
public class TestRunner {
}