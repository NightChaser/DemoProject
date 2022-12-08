package org.utils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"org/stepdefinitions", "org/utils"},
    plugin = {
      "json:target/cucumber.json",
      "html:target/default-cucumber-reports.html",
      "rerun:target/rerun.txt"
    },
    monochrome = true)
public class CucumberRunner extends AbstractTestNGCucumberTests {
  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
