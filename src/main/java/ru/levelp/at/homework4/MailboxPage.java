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
    private static WebElement listOfMails;

    @FindBy(className="user-account")
    private static WebElement avatar;

    @FindBy(css="[aria-label='Мои папки, свернуто']")
    private static WebElement customFolderCollapse;

    @FindBy(css="[href$='folder/9']")
    private static WebElement testFolder;

    @FindBy(className="mail-MessageSnippet-FromText")
    private static WebElement fromWhom;

    @FindBy(className="mail-MessageSnippet-Item_subject")
    private static WebElement subjectOfMail;

    @FindBy(className="mail-MessageSnippet-Item_firstline")
    private static WebElement textOfMail;

    @FindBy(css="span[title='lvluphomework@yandex.ru']")
    private static WebElement sender;

    @FindBy(className="_nb-checkbox-normal-flag")
    private static WebElement checkBox;

    @FindBy(className="ns-view-toolbar-button-delete")
    private static WebElement buttonDelete;

    @FindBy(css="[href$='trash']")
    private static WebElement folderTrash;

    public static WebElement getSender() {
        return sender;
    }

    public  WebElement getFromWhom() {
        wait.until(ExpectedConditions.visibilityOf(fromWhom));
        return fromWhom;
    }

    public static WebElement getSubjectOfMail() {
        return subjectOfMail;
    }

    public static WebElement getTextOfMail() {
        return textOfMail;
    }

    public  WebElement getCustomFolderCollapse() {
        return customFolderCollapse;
    }

    public WebElement getListOfMails() {
        wait.until(ExpectedConditions.visibilityOf(listOfMails));
        return listOfMails;
    }

    public WebElement getEmptyDraft() {
        wait.until(ExpectedConditions.visibilityOf(emptyDraft));
        return emptyDraft;
    }

    public void clickFolderTrash(){
        wait.until(ExpectedConditions.visibilityOf(folderTrash));
        folderTrash.click();
    }

    public void clickDelete(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonDelete));
        buttonDelete.click();
    }

    public void clickCheckBox(){
        checkBox.click();
    }

    public void clickCustomFolderCollapse() {
        customFolderCollapse.click();
    }

    public void writeMail(){
        wait.until(ExpectedConditions.visibilityOf(writeMail)).click();
    }

    public void switchToTestFolder(){
        testFolder.click();
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
