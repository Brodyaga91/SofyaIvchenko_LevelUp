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

        var mailPage = new MailPage(driver);
        mailPage.fillDestination("lvluphomework@yandex.ru");
        mailPage.fillSubject("Тема письма");
        mailPage.fillTextField("Текст письма");
        mailPage.closeMailForm();

        mailboxPage.switchToDrafts();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span [title='Тема письма']")));
        softAssertions.assertThat(driver.findElement(By.tagName("body")).getText()).contains("Тема письма");
        softAssertions.assertThat(mailboxPage.getFromWhom().getText())
                .contains("lvluphomework@yandex.ru");
        softAssertions.assertThat(mailboxPage.getSubjectOfMail().getText()).contains("Тема письма");
        softAssertions.assertThat(mailboxPage.getTextOfMail().getText())
                .contains("Текст письма");

        mailboxPage.fallIntoMail();

        mailPage.sendMail();

        var frameWaitPage = new FrameWaitPage(driver);
        frameWaitPage.closeFrame();

        mailboxPage.switchToDrafts();

        softAssertions.assertThat(mailboxPage.getEmptyDraft().getText())
                .contains("В папке «Черновики» нет писем");

        mailboxPage.switchToSent();

        driver.navigate().refresh();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains("Тема письма");

        mailboxPage.clickAvatar();

        var popUpMenuPage = new PopUpMenuPage(driver);
        popUpMenuPage.logOut();

        softAssertions.assertAll();
    }
}


