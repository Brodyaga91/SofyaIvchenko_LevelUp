package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AuthPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "passp-field-login")
    private static WebElement loginTextBox;
    @FindBy(id = "passp:sign-in")
    private static WebElement buttonLogin;
    @FindBy(id = "passp-field-passwd")
    private static WebElement passwordTextBox;
    @FindBy(className = "Button2_type_submit")
    private static WebElement buttonLoginRefresh;
    @FindBy(css = "[data-type='login']")
    private static WebElement typeLogin;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    public void login(String login, String password){
        wait.until(ExpectedConditions.visibilityOf(typeLogin)).click();
        loginTextBox.sendKeys(login);
        buttonLogin.click();
        wait.until(ExpectedConditions.visibilityOf( passwordTextBox )).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf( buttonLoginRefresh )).click();


    }
}
