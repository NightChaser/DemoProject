package org.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
  static final Logger logger = LogManager.getLogger(Hooks.class);

  @Before(value = "@UI")
  public void setupUI(Scenario scenario) {
    logger.info("Test Setup UI");
    logger.info(":::: Starting test automation ::::");
    logger.info("Browser type :: " + System.getProperty("browser"));
    logger.info("Test scenario :: " + scenario.getName());
    Driver.getDriver().manage().window().setSize(new Dimension(1920, 1080));
  }

  @After(value = "@UI")
  public void tearDownUI(Scenario scenario) {
    if (scenario.isFailed()) {
      final byte[] screenshot =
          ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
      scenario.attach(
          screenshot,
          "image/png",
          "FailureScreenshot"
              + new SimpleDateFormat("MMddyyyyhhmmss").format(new Date())
              + scenario.getName());
    }
    logger.info("Test clean up UI");
    Driver.closeDriver();
  }
}
