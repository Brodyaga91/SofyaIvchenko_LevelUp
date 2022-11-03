package ru.levelp.at.homework4;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


public class Task1Test extends BaseTest {

    @Test
    public void workWithDraft()  {

        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.logoAuth();

        var authPage = new AuthPage(driver);
        authPage.login("lvluphomework","lvluphomework123");

        mainPage.enterMail();

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        softAssertions.assertThat(driver.getTitle()).contains("Яндекс Почта");

        var mailboxPage = new MailboxPage(driver);
        mailboxPage.writeMail();

        //WebElement destination = wait
        //        .until(ExpectedConditions.visibilityOfElementLocated(By.className("composeYabbles")));
        //destination.sendKeys("lvluphomework@yandex.ru");
        //WebElement subject = driver.findElement(By.className("composeTextField"));
        //subject.sendKeys("Тема письма");
        //WebElement textField = driver.findElement(By.className("cke_wysiwyg_div"));
        //textField.click();
        //textField.sendKeys("Текст письма");
        //WebElement buttonClose = driver.findElement(By.className("qa-ControlButton_button_close"));
        //buttonClose.click();

        WebElement draftFolder = driver.findElement(By.cssSelector("[href$='draft']"));
        draftFolder.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span [title='Тема письма']")));
        softAssertions.assertThat(driver.findElement(By.tagName("body")).getText()).contains("Тема письма");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-FromText")).getText())
                .contains("lvluphomework@yandex.ru");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_subject")).getText()).contains("Тема письма");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_firstline")).getText())
                .contains("Текст письма");
        WebElement myMail = driver.findElement(By.className("mail-MessageSnippet-Item_subject"));
        myMail.click();
        WebElement sendMail = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeSendButton")));
        sendMail.click();
        WebElement closeFrame = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeDoneScreen-Actions")));
        closeFrame.click();
        draftFolder.click();
        softAssertions.assertThat(driver
                .findElement(By.className("ns-view-messages-empty")).getText())
                .contains("В папке «Черновики» нет писем");
        WebElement sentFolder = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href$='sent']")));
        sentFolder.click();
        driver.navigate().refresh();
        WebElement listOfSent = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Content")));
        softAssertions.assertThat(listOfSent.getText()).contains("Тема письма");
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(By.className("user-account")));
        avatar.click();
        WebElement logOut = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("legouser__menu-item_action_exit")));
        logOut.click();
        softAssertions.assertAll();
    }
}


