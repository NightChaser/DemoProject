package org.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleHomePage extends BasePage {

  public GoogleHomePage() {
    super();
  }

  @FindBy(xpath = "//input[@title='Search']")
  public WebElement searchBar;

  @FindBy(xpath = "(//input[@value='Google Search'])[2]")
  public WebElement searchButton;

  @FindBy(xpath = "//h3")
  public List<WebElement> resultHeaders;
}
