package ru.levelp.at.homework4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    private static final String URL_MAIL = "https://dzen.ru/";

    @FindBy(className = "dzen-header-desktop__isUnauthorized-2e")
    private static WebElement buttonLoginMainPage;

    @FindBy(className = "dzen-search-arrow-common")
    private static WebElement searchTextBox;

    @FindBy(css = "iframe.dzen-search-arrow-common__frame")
    private static WebElement frame;

    @FindBy(className = "home-link2")
    private static WebElement buttonMail;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to(URL_MAIL);
    }

    public void logoAuth() {
        buttonLoginMainPage.click();
    }

    public void enterMail() {
        wait.until(ExpectedConditions.visibilityOf(searchTextBox)).click();
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.visibilityOf(buttonMail)).click();
    }
}
