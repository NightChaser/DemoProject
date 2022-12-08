package org.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.utils.Driver;

import java.util.ArrayList;

public abstract class BasePage {

  public BasePage() {
    PageFactory.initElements(Driver.getDriver(), this);
  }

  public void scrollDown() {
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    js.executeScript("window.scrollBy(0,2000)");
  }

  public void scrollUp() {
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    js.executeScript("window.scrollBy(0,-2000)");
  }

  public void navigateBack() {
    Driver.getDriver().navigate().back();
  }

  public void navigateToSecondTab() {
    ArrayList<String> tabs = new ArrayList(Driver.getDriver().getWindowHandles());
    if (tabs.size() >= 2) {
      Driver.getDriver().switchTo().window(tabs.get(1));
    } else {
      throw new NullPointerException("Second tab not found");
    }
  }

  public void closeSecondTab() {
    ArrayList<String> tabs = new ArrayList(Driver.getDriver().getWindowHandles());
    if (tabs.size() >= 2) {
      Driver.getDriver().close();
      Driver.getDriver().switchTo().window(tabs.get(0));
    } else {
      throw new NullPointerException("Second tab not found");
    }
  }

  public void refreshPage() {
    Driver.getDriver().navigate().refresh();
  }
}
