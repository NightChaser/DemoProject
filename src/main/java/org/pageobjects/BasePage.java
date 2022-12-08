package org.pageobjects;

import org.openqa.selenium.support.PageFactory;
import org.utils.Driver;


public abstract class BasePage {

  public BasePage() {
    PageFactory.initElements(Driver.getDriver(), this);
  }

}
