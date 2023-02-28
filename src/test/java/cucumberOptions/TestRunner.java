package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue={"stepdefination"},plugin= "json:target/JsonReports/cucumber-report.json")

// tags= "@DeletePlaceAPI" - if you want execute only one test case then you need use this like a @CucumberOptions(features="src/test/java/features",glue={"stepdefination"},tags= "@DeletePlaceAPI")
// plugin= "json:target/JsonReports/cucumber-report.json" - this will generate the reports in target folder in that folder will create JsonReports folder and in that folder will create the cucumber-report.json file
// target = we are given target folder because in pom.xml file will give the directory like a ${project.build.directory} this will treat like a until target folder
//json:target =here json means we are saying like a to create json file
public class TestRunner {

}
