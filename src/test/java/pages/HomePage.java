package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import utilities.WaitUtils;

import java.time.Duration;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logoutlink;

    @FindBy(xpath = "//a[text()='Transfer Funds']")
    public WebElement transferfundslink;

    @FindBy(xpath = "//a[text()='Bill Pay']")
    public WebElement billpaylink;

    @FindBy(xpath = "//p[@class='smallText']")
    public WebElement loggedInUsername;

    public void clickLogout(){
        WaitUtils.waitForClickable(driver, By.xpath("//a[text()='Logout']"));
        logoutlink.click();
    }

     public void clickTransferFunds(){
         WaitUtils.waitForClickable(driver, By.xpath("//a[text()='Transfer Funds']"));
         transferfundslink.click();
     }

     public void clickBillPay(){
         WaitUtils.waitForClickable(driver, By.xpath("//a[text()='Bill Pay']"));
         billpaylink.click();
     }

     public String getUsername(){
        WaitUtils.waitForVisibility(driver, By.xpath("//p[@class='smallText']"));
        return loggedInUsername.getText();
     }
 }
