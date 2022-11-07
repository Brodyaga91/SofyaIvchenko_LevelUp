package ru.levelp.at.homework4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class Task2Test extends BaseTest {


    @Test
    public void checkFilter() {

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
        mailPage.fillSubject("тема Тест");
        mailPage.fillTextField("Текст письма Тест");
        mailPage.sendMail();

        var frameWaitPage = new FrameWaitPage(driver);
        frameWaitPage.closeFrame();

        mailboxPage.switchToSent();

        driver.navigate().refresh();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains("тема Тест");

        if (mailboxPage.getCustomFolderCollapse().isDisplayed()) {
            mailboxPage.clickCustomFolderCollapse();
        }

        mailboxPage.switchToTestFolder();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains("тема Тест");
        softAssertions.assertThat(mailboxPage.getFromWhom().getText())
                .contains("lvluphomework@yandex.ru");
        softAssertions.assertThat(mailboxPage.getSubjectOfMail()
                .getText()).contains("тема Тест");
        softAssertions.assertThat(
                          mailboxPage.getTextOfMail().getText())
                .contains("Текст письма Тест");

        mailboxPage.clickAvatar();

        var popUpMenuPage = new PopUpMenuPage(driver);
        popUpMenuPage.logOut();

        softAssertions.assertAll();
    }

}
