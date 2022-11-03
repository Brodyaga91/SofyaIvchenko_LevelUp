package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailPage extends BasePage{

    @FindBy(className="composeYabbles")
    private static WebElement destination;

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public void fillDestination(String destinationText){
        wait.until(ExpectedConditions.visibilityOf(destination)).sendKeys(destinationText);
    }


    //WebElement subject = driver.findElement(By.className("composeTextField"));
    //subject.sendKeys("Тема письма");
    //WebElement textField = driver.findElement(By.className("cke_wysiwyg_div"));
    //textField.click();
    //textField.sendKeys("Текст письма");
    //WebElement buttonClose = driver.findElement(By.className("qa-ControlButton_button_close"));
    //buttonClose.click();
}
