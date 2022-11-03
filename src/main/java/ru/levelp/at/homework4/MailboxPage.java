package ru.levelp.at.homework4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailboxPage extends BasePage{

    public MailboxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className="qa-LeftColumn-ComposeButton")
        private static WebElement writeMail;

    public void writeMail(){
        wait
            .until(ExpectedConditions.visibilityOf(writeMail)).click();
    }

}
