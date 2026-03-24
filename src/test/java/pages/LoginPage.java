package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Log4j;
import utilities.WaitUtils;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

 // object repository for login page
 // WebElement username=  driver.findElement(By.name("username"));
 //   By username=  By.name("username");

    @FindBy(name="username")
    public WebElement usernamefield;

    @FindBy(name="password")
    public WebElement passwordfield;

    @FindBy(xpath = "//input[@value='Log In']")
    public WebElement loginbutton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver, this);

    }

    public void sendUsername(String username){
        WaitUtils.waitForVisibility(driver, By.name("username"));
        usernamefield.clear();
        Log4j.info(LoginPage.class, "Entering username: " + username);
        usernamefield.sendKeys(username);
    }

    public void sendPassword(String password){
        WaitUtils.waitForVisibility(driver, By.name("password"));
        passwordfield.clear();
        Log4j.info(LoginPage.class, "Entering password: " + (password.length() > 0 ? "******" : "(empty)"));
        passwordfield.sendKeys(password);
    }

    public void clickLogin(){
        WaitUtils.waitForClickable(driver, By.xpath("//input[@value='Log In']"));
        loginbutton.click();
    }

    public void login(String username, String password){
        sendUsername(username);
        Log4j.info(LoginPage.class, "Username entered: " + username);
        sendPassword(password);
            Log4j.info(LoginPage.class, "Password entered: " + (password.length() > 0 ? "******" : "(empty)"));
        clickLogin();
        Log4j.info(LoginPage.class, "Login button clicked");
    }

}
