package ru.levelp.at.homework4;

import java.util.ArrayList;
import org.testng.annotations.Test;


public class Task1Test extends BaseTest {

    @Test
    public void workWithDraft()  {

        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.logoAuth();

        var authPage = new AuthPage(driver);
        authPage.login(TestConfiguration.TEST_LOGIN, TestConfiguration.TEST_PASS);

        mainPage.enterMail();

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        softAssertions.assertThat(driver.getTitle()).contains("Яндекс Почта");

        var mailboxPage = new MailboxPage(driver);
        mailboxPage.writeMail();

        var mailPage = new MailPage(driver);
        mailPage.fillDestination(TestConfiguration.TEST_EMAIL);
        mailPage.fillSubject(TestConfiguration.TEST_SUBJECT1);
        mailPage.fillTextField(TestConfiguration.TEST_TEXT1);
        mailPage.closeMailForm();

        mailboxPage.switchToDrafts();

        softAssertions.assertThat(mailboxPage.getBody().getText()).contains(TestConfiguration.TEST_SUBJECT1);
        softAssertions.assertThat(mailboxPage.getFromWhom().getText())
                .contains(TestConfiguration.TEST_EMAIL);
        softAssertions.assertThat(mailboxPage.getSubjectOfMail().getText()).contains(TestConfiguration.TEST_SUBJECT1);
        softAssertions.assertThat(mailboxPage.getTextOfMail().getText())
                .contains(TestConfiguration.TEST_TEXT1);

        mailboxPage.fallIntoMail();

        mailPage.sendMail();

        var frameWaitPage = new FrameWaitPage(driver);
        frameWaitPage.closeFrame();

        mailboxPage.switchToDrafts();

        softAssertions.assertThat(mailboxPage.getEmptyDraft().getText())
                .contains("В папке «Черновики» нет писем");

        mailboxPage.switchToSent();

        driver.navigate().refresh();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains(TestConfiguration.TEST_SUBJECT1);

        mailboxPage.clickAvatar();

        var popUpMenuPage = new PopUpMenuPage(driver);
        popUpMenuPage.logOut();

        softAssertions.assertAll();
    }
}


