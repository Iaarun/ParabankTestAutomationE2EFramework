package hooks;

import base.BrowserManager;
import io.cucumber.java.After;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;
import utilities.Log4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @Before
    public void setUp(){
        Log4j.info(Hooks.class, "Test setup starting");
        // ensure driver binary is available
        WebDriverManager.chromedriver().setup();
        WebDriver driver = BrowserManager.getDriver();
        String url = ConfigReader.getAppUrl();
        if(url!=null && !url.isEmpty()){
            Log4j.info(Hooks.class, "Navigating to: " + url);
            driver.get(url);
        } else {
            Log4j.warn(Hooks.class, "Application URL not configured; skipping navigation.");
        }
        Log4j.info(Hooks.class, "Test setup completed");
    }

    @After
    public void tearDown(Scenario scenario){
        // on failure capture screenshot and attach to cucumber report

        try{
            if(scenario!=null){
                Log4j.info(Hooks.class, "Scenario finished: " + scenario.getName() + " - status: " + (scenario.isFailed() ? "FAILED" : "PASSED"));
            }
            if(scenario!=null && scenario.isFailed()) {
                WebDriver driver = BrowserManager.getDriver();
                if (driver != null && driver instanceof TakesScreenshot) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                    String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                    String fileName = "target/screenshots/" + safeName + "_" + timestamp + ".png";
                    Path dest = Paths.get(fileName);
                    Files.createDirectories(dest.getParent());
                    Files.write(dest, screenshot);
                    Log4j.info(Hooks.class, "Saved screenshot to: " + fileName);
                    scenario.attach(screenshot, "image/png", fileName);
                } else {
                    Log4j.warn(Hooks.class, "Driver does not support screenshots or is null; cannot capture screenshot for failed scenario.");
                }
            }
        }catch(Exception e){
            Log4j.error(Hooks.class, "Error in tearDown hook", e);
        } finally {
            Log4j.info(Hooks.class, "Closing browser for scenario: " + (scenario!=null ? scenario.getName() : "unknown"));
            BrowserManager.closeBrowser();
        }
    }
}

