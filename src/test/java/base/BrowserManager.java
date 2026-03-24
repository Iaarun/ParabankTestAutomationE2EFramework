package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BrowserManager {
// WebDriver driver;
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

public static WebDriver getDriver() {
     WebDriver dr = driver.get();
     if(dr==null){
         ChromeOptions options = new ChromeOptions();
         String headless = System.getProperty("headless", "false");
         if(headless.equalsIgnoreCase("true")){
             options.addArguments("--headless=new");
             options.addArguments("--window-size=1920,1080");
         }
         options.addArguments("--headless");
         dr = new ChromeDriver(options);
         dr.manage().window().maximize();
         dr.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
         driver.set(dr);
     }

  return dr;
 }

 public static void  closeBrowser(){
     WebDriver dr =driver.get();
        if(dr!=null){
            dr.quit();
            driver.remove();
        }
 }

    public static void main(String[] args) {
     WebDriver dr=  BrowserManager.getDriver();
     dr.get("https://www.google.com/");
        System.out.println("Title: "+dr.getTitle());
          BrowserManager.closeBrowser();

    }
}
