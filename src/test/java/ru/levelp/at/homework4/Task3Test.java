package ru.levelp.at.homework4;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


public class Task3Test extends BaseTest {

    @Test
    public void checkInboxAndTrash() {

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
        mailPage.fillSubject("тема Задание 3");
        mailPage.fillTextField("Текст письма Задание 3");
        mailPage.sendMail();

        var frameWaitPage = new FrameWaitPage(driver);
        frameWaitPage.closeFrame();

        driver.navigate().refresh();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains("тема Задание 3");
        softAssertions.assertThat(mailboxPage.getSender().getAttribute("title"))
                .isEqualTo("lvluphomework@yandex.ru");
        softAssertions.assertThat(mailboxPage.getSubjectOfMail().getText())
                .contains("тема Задание 3");
        softAssertions.assertThat(mailboxPage.getTextOfMail().getText())
                .contains("Текст письма Задание 3");

        mailboxPage.clickCheckBox();
        mailboxPage.clickDelete();
        mailboxPage.clickFolderTrash();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains("тема Задание 3");

        mailboxPage.clickAvatar();

        var popUpMenuPage = new PopUpMenuPage(driver);
        popUpMenuPage.logOut();

        softAssertions.assertAll();
    }
}
