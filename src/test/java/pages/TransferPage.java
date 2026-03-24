package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log4j;

public class TransferPage {

    WebDriver driver;

    @FindBy(name = "amount")
    public WebElement amountField;

    @FindBy(xpath = "//input[@value='Transfer']")
    public WebElement transferButton;

    // a friendly success message element - try to capture common success texts
    @FindBy(xpath = "//*[contains(text(),'Transfer Complete') or contains(text(),'was successful') or contains(text(),'successfully')]")
    public WebElement successMessage;

    public TransferPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void enterAmount(String amount){
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        Log4j.info(TransferPage.class, "Locating the element");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("amount")));
        amountField.clear();
        amountField.sendKeys(amount);
        Log4j.info(TransferPage.class, "Entered amount: " + amount);
    }

    public void clickTransfer(){
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Transfer']")));
        transferButton.click();
    }

    public String getSuccessMessage(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Transfer Complete') or contains(text(),'was successful') or contains(text(),'successfully') ]")),
                    ExpectedConditions.titleContains("Transfer")
            ));
            try{
                return successMessage.getText();
            }catch(Exception e){
                return driver.getTitle();
            }
        }catch(Exception e){
            // fallback
            try{ return driver.getTitle(); } catch(Exception ex){ return ""; }
        }
    }
}
