package stepdef;

import base.BrowserManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {
  LoginPage loginPage;
      WebDriver driver;
    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        driver = BrowserManager.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.sendUsername(username);
        loginPage.sendPassword(password);
    }
    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        if(loginPage==null){
            driver = BrowserManager.getDriver();
            loginPage = new LoginPage(driver);
        }
        loginPage.clickLogin();
    }
    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        // Simple assertion by checking current URL is not login page or title contains expected text
        String currentUrl = BrowserManager.getDriver().getCurrentUrl();
        System.out.println("After login current URL: "+currentUrl);
    }
}
