package org.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {

  private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

  private Driver() {}

  public static synchronized WebDriver getDriver() {

    if (driverPool.get() == null) {
      String browser = Property.getProperty("browser").toLowerCase();
      if (System.getProperty("browser") != null) {
        browser = System.getProperty("browser");
      }
      switch (browser) {
        case "chrome":
          WebDriverManager.chromedriver().setup();
          ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.addArguments("--disable-notifications");
          chromeOptions.addArguments("--incognito");
          driverPool.set(new ChromeDriver(chromeOptions));
          break;
        case "chromeheadless":
          WebDriverManager.chromedriver().setup();
          ChromeOptions chromeHeadlessOptions = new ChromeOptions();
          chromeHeadlessOptions.setHeadless(true);
          chromeHeadlessOptions.addArguments("--disable-notifications");
          chromeHeadlessOptions.addArguments("--incognito");
          driverPool.set(new ChromeDriver(chromeHeadlessOptions));
          break;
        case "firefox":
          WebDriverManager.firefoxdriver().setup();
          FirefoxOptions firefoxOptions = new FirefoxOptions();
          firefoxOptions.addPreference("dom.webnotifications.enabled", false);
          firefoxOptions.addArguments("-private");
          driverPool.set(new FirefoxDriver(firefoxOptions));
          break;
        case "firefoxheadless":
          WebDriverManager.firefoxdriver().setup();
          FirefoxOptions firefoxHeadlessOptions = new FirefoxOptions();
          firefoxHeadlessOptions.setHeadless(true);
          firefoxHeadlessOptions.addPreference("dom.webnotifications.enabled", false);
          firefoxHeadlessOptions.addArguments("-private");
          driverPool.set(new FirefoxDriver(firefoxHeadlessOptions));
          break;
        case "edge":
          WebDriverManager.edgedriver().setup();
          EdgeOptions edgeOptions = new EdgeOptions();
          edgeOptions.setCapability("UseChromium", true);
          edgeOptions.addArguments("--inprivate");
          driverPool.set(new EdgeDriver(edgeOptions));
          break;
        case "edgeheadless":
          WebDriverManager.edgedriver().setup();
          EdgeOptions edgeHeadlessOptions = new EdgeOptions();
          edgeHeadlessOptions.setCapability("UseChromium", true);
          edgeHeadlessOptions.addArguments("--headless");
          edgeHeadlessOptions.addArguments("--inprivate");
          driverPool.set(new EdgeDriver(edgeHeadlessOptions));
          break;
        default:
          throw new RuntimeException("Wrong browser name!");
      }
    }
    return driverPool.get();
  }

  public static void closeDriver() {
    if (driverPool.get() != null) {
      driverPool.get().quit();
      driverPool.remove();
    }
  }
}
