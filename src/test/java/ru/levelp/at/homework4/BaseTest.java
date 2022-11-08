package ru.levelp.at.homework4;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTest {

    //private static final String URL_MAIL = "https://dzen.ru/";

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final SoftAssertions softAssertions = new SoftAssertions();

    @BeforeMethod
    public void activateDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }

    @AfterMethod
    public void finishDriver() {
        driver.quit();
    }
}
