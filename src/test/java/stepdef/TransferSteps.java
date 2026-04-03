package stepdef;

import base.BrowserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.TransferPage;
import utilities.ConfigReader;
import utilities.WaitUtils;

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
        // wait explicitly for transfer page main field to appear to avoid timing issues
        try{
            WaitUtils.waitForVisibility(BrowserManager.getDriver(), By.name("amount"), 15);
        } catch(Exception e){
            // log and continue – TransferPage will also perform waits and will report failures with more detail
            System.out.println("Warning: Amount field not immediately visible on Transfer Page. Timing issue possible.");
        }
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
