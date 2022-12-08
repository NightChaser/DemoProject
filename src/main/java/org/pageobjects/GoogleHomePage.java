package org.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.Driver;

import java.util.List;

public class GoogleHomePage extends BasePage {

  public GoogleHomePage() {
    PageFactory.initElements(Driver.getDriver(), this);
  }

  @FindBy(xpath = "//input[@title='Search']")
  public WebElement searchBar;

  @FindBy(xpath = "(//input[@value='Google Search'])[2]")
  public WebElement searchButton;

  @FindBy(xpath = "//h3")
  public List<WebElement> resultHeaders;
}
