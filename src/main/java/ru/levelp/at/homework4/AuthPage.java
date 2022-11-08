package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AuthPage extends BasePage {

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
        super(driver);
    }

    public void login(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(typeLogin)).click();
        loginTextBox.sendKeys(login);
        buttonLogin.click();
        wait.until(ExpectedConditions.visibilityOf(passwordTextBox)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(buttonLoginRefresh)).click();


    }
}
