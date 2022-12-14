package ru.levelp.at.homework4;

import java.util.ArrayList;
import org.testng.annotations.Test;

public class Task2Test extends BaseTest {


    @Test
    public void checkFilter() {

        var mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.logoAuth();
        var authPage = new AuthPage(driver);
        authPage.login(GetProperty.getProperty("TEST_LOGIN"), GetProperty.getProperty("TEST_PASS"));

        mainPage.enterMail();

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        softAssertions.assertThat(driver.getTitle()).contains("Яндекс Почта");

        var mailboxPage = new MailboxPage(driver);
        mailboxPage.writeMail();

        var mailPage = new MailPage(driver);
        mailPage.fillDestination(GetProperty.getProperty("TEST_EMAIL"));
        mailPage.fillSubject(TestConfiguration.TEST_SUBJECT2);
        mailPage.fillTextField(TestConfiguration.TEST_TEXT2);
        mailPage.sendMail();

        var frameWaitPage = new FrameWaitPage(driver);
        frameWaitPage.closeFrame();

        mailboxPage.switchToSent();

        driver.navigate().refresh();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains(TestConfiguration.TEST_SUBJECT2);

        if (mailboxPage.getCustomFolderCollapse().isDisplayed()) {
            mailboxPage.clickCustomFolderCollapse();
        }

        mailboxPage.switchToTestFolder();

        softAssertions.assertThat(mailboxPage.getListOfMails().getText()).contains(TestConfiguration.TEST_SUBJECT2);
        softAssertions.assertThat(mailboxPage.getFromWhom().getText())
                .contains(GetProperty.getProperty("TEST_EMAIL"));
        softAssertions.assertThat(mailboxPage.getSubjectOfMail()
                .getText()).contains(TestConfiguration.TEST_SUBJECT2);
        softAssertions.assertThat(
                          mailboxPage.getTextOfMail().getText())
                .contains(TestConfiguration.TEST_TEXT2);

        mailboxPage.clickAvatar();

        var popUpMenuPage = new PopUpMenuPage(driver);
        popUpMenuPage.logOut();

        softAssertions.assertAll();
    }

}
