package org.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.models.enums.WaitTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

  static final Logger logger = LogManager.getLogger(BrowserUtils.class);

  public static void clickWithJS(WebElement element) {
    waitForVisibility(element);
    ((JavascriptExecutor) Driver.getDriver())
        .executeScript("arguments[0].scrollIntoView(true);", element);
    ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
  }

  public static void clickWithJS(WebElement element, String elementNameLog) {
    waitForVisibility(element, elementNameLog);
    logger.info("Clicking on " + elementNameLog + "with JS");
    ((JavascriptExecutor) Driver.getDriver())
        .executeScript("arguments[0].scrollIntoView(true);", element);
    ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
  }

  public static void scrollTo(WebElement element) {
    ((JavascriptExecutor) Driver.getDriver())
        .executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public static void clickOnElement(WebElement element, String elementNameLog) {
    try {
      waitForVisibility(element, elementNameLog);
      logger.info("Clicking on " + elementNameLog);
      element.click();
    } catch (Exception e) {
      logger.error("Can't click on " + elementNameLog + ". " + e.getMessage());
      throw new ElementNotInteractableException("");
    }
  }

  public static void sendKeys(WebElement element, String value, String elementNameLog) {
    try {
      waitForVisibility(element, elementNameLog);
      element.clear();
      logger.info("Sending " + value + " to " + elementNameLog);
      element.sendKeys(value);
    } catch (Exception e) {
      logger.error(
          "Can't send "
              + value
              + " in to element: "
              + elementNameLog
              + ". Error: "
              + Arrays.toString(e.getStackTrace()));
      throw new ElementNotInteractableException("");
    }
  }

  public static WebElement waitForVisibility(WebElement element, String elementNameLog) {
    logger.info("Waiting for visibility of element " + elementNameLog);
    WebDriverWait wait =
        new WebDriverWait(
            Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(WaitTime.VISIBILITY)));
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  public static WebElement waitForVisibility(WebElement element) {
    WebDriverWait wait =
        new WebDriverWait(
            Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(WaitTime.VISIBILITY)));
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  public static WebElement waitForClickability(WebElement element, String elementNameLog) {
    logger.info("Waiting for clickability of element " + elementNameLog);
    WebDriverWait wait =
        new WebDriverWait(
            Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(WaitTime.VISIBILITY)));
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public static Boolean isElementVisible(WebElement element, String elementNameLog) {
    waitForVisibility(element, elementNameLog);
    return element.isDisplayed();
  }

  public static Boolean isElementVisible(WebElement element) {
    waitForVisibility(element);
    return element.isDisplayed();
  }

  public static void switchToIframe(WebDriver driver, WebElement frame) {
    driver.switchTo().defaultContent();
    WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(Property.getWaitTime(WaitTime.VISIBILITY)));
    logger.info("Switching to iframe");
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
  }

  public static void switchToIframe(WebElement frame) {
    Driver.getDriver().switchTo().defaultContent();
    WebDriverWait wait =
        new WebDriverWait(
            Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(WaitTime.VISIBILITY)));
    logger.info("Switching to iframe");
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
  }

  public static void wait(WaitTime waitTime) {
    logger.info("Waiting " + Property.getWaitTime(waitTime) + "seconds implicitly");
    Driver.getDriver()
        .manage()
        .timeouts()
        .implicitlyWait(Duration.ofSeconds(Property.getWaitTime(waitTime)));
  }

  public static String getElementText(WebElement element, String elementNameLog) {
    logger.info("Getting element " + elementNameLog + " text");
    waitForVisibility(element, elementNameLog);
    return element.getText();
  }

  public static Boolean isElementTextEqual(
      WebElement element, String compareTo, String elementNameLog) {
    waitForVisibility(element, elementNameLog);
    return getElementText(element, elementNameLog).equals(compareTo);
  }

  public static Boolean isUrlEqualToCurrentPage(String url) {
    return Driver.getDriver().getCurrentUrl().equals(url);
  }

  public static Boolean isUrlContains(String value) {
    return Driver.getDriver().getCurrentUrl().toLowerCase().contains(value.toLowerCase());
  }

  public static void sendKeysToDropDown(WebElement element, String value, String elementNameLog) {
    try {
      BrowserUtils.wait(WaitTime.VERY_SHORT);
      logger.info("Sending " + value + " to " + elementNameLog);
      Select select = new Select(element);
      select.selectByVisibleText(value);
    } catch (Exception e) {
      logger.error(
          "Can't send "
              + value
              + " in to dropdown element: "
              + elementNameLog
              + ". Error: "
              + Arrays.toString(e.getStackTrace()));
      throw new ElementNotInteractableException("");
    }
  }

  public static void sendKeysToDropDownByIndex(
      WebElement element, int index, String elementNameLog) {
    try {
      BrowserUtils.wait(WaitTime.VERY_SHORT);
      waitForVisibility(element);
      logger.info("Sending " + index + " to " + elementNameLog);
      Select select = new Select(element);
      select.selectByIndex(index);
    } catch (Exception e) {
      logger.error(
          "Can't send "
              + index
              + " in to dropdown element: "
              + elementNameLog
              + ". Error: "
              + Arrays.toString(e.getStackTrace()));
      throw new ElementNotInteractableException("");
    }
  }

  public static void sendKeysToDropDownByValue(
      WebElement element, String value, String elementNameLog) {
    try {
      BrowserUtils.wait(WaitTime.VERY_SHORT);
      logger.info("Sending " + value + " to " + elementNameLog);
      Select select = new Select(element);
      select.selectByValue(value);
    } catch (Exception e) {
      logger.error(
          "Can't send "
              + value
              + " in to dropdown element: "
              + elementNameLog
              + ". Error: "
              + Arrays.toString(e.getStackTrace()));
      throw new ElementNotInteractableException("");
    }
  }

  public static void logInfo(String message) {
    logger.info(message);
  }

  public static WebElement getWebElementByXpath(String xpath) {
    return Driver.getDriver().findElement(By.xpath(xpath));
  }

  public static boolean isElementVisible(WebElement element, WaitTime waitTime) {
    logger.info("Waiting on Element Visibility " + element);
    WebDriverWait wait =
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(waitTime)));
    try {
      wait.until(ExpectedConditions.visibilityOf(element));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static void switchToDefaultContent() {
    Driver.getDriver().switchTo().defaultContent();
  }

  public static void waitSleep(WaitTime waitTime) {
    try {
      logger.info("Wait sleep for " + Property.getWaitTime(waitTime));
      Thread.sleep(Property.getWaitTime(waitTime) * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void switchWindow(String title) {
    Set<String> windowHandles = Driver.getDriver().getWindowHandles();
    for (String window : windowHandles) {
      Driver.getDriver().switchTo().window(window);
      if (Driver.getDriver().getTitle().equals(title)) {
        break;
      }
    }
  }

  public static boolean isElementClickable(WebElement element) {
    waitForVisibility(element);
    try {
      new WebDriverWait(
              Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(WaitTime.SHORT)))
          .until(ExpectedConditions.elementToBeClickable(element));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean isElementClickable(WebElement element, String elementNameLog) {
    logger.info("Waiting for clickability of element " + elementNameLog);
    waitForVisibility(element, elementNameLog);
    try {
      new WebDriverWait(
              Driver.getDriver(), Duration.ofSeconds(Property.getWaitTime(WaitTime.SHORT)))
          .until(ExpectedConditions.elementToBeClickable(element));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean isElementsListEmpty(List<WebElement> list, String elementNameLog) {
    waitForVisibility(list.get(0), elementNameLog);
    return list.size() > 0;
  }

  public static void openUrl(String str) {
    Driver.getDriver().navigate().to(str);
  }
}
