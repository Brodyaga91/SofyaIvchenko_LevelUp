package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailPage extends BasePage {

    @FindBy(className = "composeYabbles")
    private static WebElement destination;

    @FindBy(className = "composeTextField")
    private static WebElement subject;

    @FindBy(className = "cke_wysiwyg_div")
    private static WebElement textField;

    @FindBy(className = "ComposeSendButton")
    private static WebElement sendMail;

    @FindBy(className = "qa-ControlButton_button_close")
    private static WebElement buttonClose;

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public void fillDestination(String destinationText) {
        wait.until(ExpectedConditions.visibilityOf(destination)).sendKeys(destinationText);
    }

    public void fillSubject(String subjectText) {
        subject.sendKeys(subjectText);
    }

    public void fillTextField(String textOfMail) {
        textField.click();
        textField.sendKeys(textOfMail);
    }

    public void closeMailForm() {
        buttonClose.click();
    }

    public void sendMail() {
        wait.until(ExpectedConditions.visibilityOf(sendMail)).click();
    }


}
