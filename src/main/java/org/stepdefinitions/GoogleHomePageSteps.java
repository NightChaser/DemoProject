package org.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pageobjects.GoogleHomePage;

import static org.testng.Assert.assertTrue;
import static org.utils.BrowserUtils.*;

public class GoogleHomePageSteps {
  GoogleHomePage googleHomePage = new GoogleHomePage();

  @Given("User is on {string} page")
  public void userInOnGoogleComPage(String str) {
    openUrl(str);
  }

  @When("User enter {string} in search box")
  public void userEnterWikipediaInSearchBox(String str) {
    sendKeys(googleHomePage.searchBar, str, "googleHomePage.searchBar");
  }

  @And("User click search button")
  public void userClickSearchButton() {
    clickOnElement(googleHomePage.searchButton, "googleHomePage.searchButton");
  }

  @Then("User can see multiple search results")
  public void userCanSeeMultipleSearchResults() {
    assertTrue(googleHomePage.resultHeaders.size() > 0);
  }
}
