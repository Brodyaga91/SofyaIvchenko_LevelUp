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

    @FindBy(css="[href$='draft']")
    private static WebElement draftFolder;

    @FindBy(className="mail-MessageSnippet-Item_subject")
    private static WebElement myMail;

    @FindBy(className="ns-view-messages-empty")
    private static WebElement emptyDraft;

    @FindBy(css="[href$='sent']")
    private static WebElement sentFolder;

    @FindBy(className="mail-MessageSnippet-Content")
    private static WebElement listOfSent;

    @FindBy(className="user-account")
    private static WebElement avatar;

    public WebElement getListOfSent() {
        wait.until(ExpectedConditions.visibilityOf(listOfSent));
        return listOfSent;
    }

    public WebElement getEmptyDraft() {
        wait.until(ExpectedConditions.visibilityOf(emptyDraft));
        return emptyDraft;
    }

    public void writeMail(){
        wait.until(ExpectedConditions.visibilityOf(writeMail)).click();
    }

    public void switchToDrafts(){
        draftFolder.click();
    }

    public void fallIntoMail(){
        myMail.click();
    }

    public void switchToSent(){
        sentFolder.click();
    }

    public void clickAvatar(){
        avatar.click();
    }


}
