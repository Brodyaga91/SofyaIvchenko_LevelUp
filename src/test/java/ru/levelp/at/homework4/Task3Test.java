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

        WebElement closeFrame = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeDoneScreen-Actions")));
        closeFrame.click();
        driver.navigate().refresh();
        WebElement listOfSent = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Content")));
        softAssertions.assertThat(listOfSent.getText()).contains("тема Задание 3");
        softAssertions.assertThat(
                driver.findElement(By.cssSelector("span[title='lvluphomework@yandex.ru']")).getAttribute("title"))
                .isEqualTo("lvluphomework@yandex.ru");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_subject")).getText())
                .contains("тема Задание 3");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_firstline")).getText())
                .contains("Текст письма Задание 3");
        WebElement checkBox = driver.findElement(By.className("_nb-checkbox-normal-flag"));
        checkBox.click();
        WebElement buttonDelete = wait
                .until(ExpectedConditions.elementToBeClickable(By.className("ns-view-toolbar-button-delete")));
        buttonDelete.click();
        WebElement folderTrash = driver.findElement(By.cssSelector("[href$='trash']"));
        folderTrash.click();
        softAssertions.assertThat(listOfSent.getText()).contains("тема Задание 3");
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(By.className("user-account")));
        avatar.click();
        WebElement logOut = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("legouser__menu-item_action_exit")));
        logOut.click();
        softAssertions.assertAll();
    }
}
