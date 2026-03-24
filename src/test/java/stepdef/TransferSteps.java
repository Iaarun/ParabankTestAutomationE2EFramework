package stepdef;

import base.BrowserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.TransferPage;
import utilities.ConfigReader;

public class TransferSteps {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    TransferPage transferPage;

    @Given("user logs into application")
    public void user_logs_into_application() {
        driver = BrowserManager.getDriver();
        // navigate to the application home/login
        String url = ConfigReader.getAppUrl();
        if(url!=null && !url.isEmpty()){
            driver.get(url);
        }
        loginPage = new LoginPage(driver);
        // use demo credentials from feature or default ones
        loginPage.login("john", "demo");
        // initialize home page elements
        homePage = new HomePage(driver);
        // click transfer funds to reach transfer page
        homePage.clickTransferFunds();
        transferPage = new TransferPage(driver);
    }

    @When("user transfers {string} amount")
    public void user_transfers_amount(String amount) {
        if(transferPage==null){
            transferPage = new TransferPage(BrowserManager.getDriver());
        }
        transferPage.enterAmount(amount);
        transferPage.clickTransfer();
    }

    @Then("transfer should be successful")
    public void transfer_should_be_successful() {
        if(transferPage==null){
            transferPage = new TransferPage(BrowserManager.getDriver());
        }
        String message = transferPage.getSuccessMessage();
        System.out.println("Transfer result message/title: " + message);
        // Basic validation: expect that either page title or success message contains keywords
        boolean ok = (message!=null && (message.toLowerCase().contains("transfer") || message.toLowerCase().contains("success") || message.toLowerCase().contains("complete")));
        Assert.assertTrue("Transfer did not appear successful. Message: " + message, ok);
    }
}
