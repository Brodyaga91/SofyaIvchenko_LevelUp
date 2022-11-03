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
        WebElement writeMail = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("qa-LeftColumn-ComposeButton")));
        writeMail.click();
        WebElement destination = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("composeYabbles")));
        destination.sendKeys("lvluphomework@yandex.ru");
        WebElement subject = driver.findElement(By.className("composeTextField"));
        subject.sendKeys("тема Тест");
        WebElement textField = driver.findElement(By.className("cke_wysiwyg_div"));
        textField.click();
        textField.sendKeys("Текст письма Тест");
        WebElement sendMail = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeSendButton")));
        sendMail.click();
        WebElement closeFrame = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeDoneScreen-Actions")));
        closeFrame.click();
        WebElement sentFolder = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href$='sent']")));
        sentFolder.click();
        driver.navigate().refresh();
        WebElement listOfMail = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Content")));
        softAssertions.assertThat(listOfMail.getText()).contains("тема Тест");
        if (driver.findElement(By.cssSelector("[aria-label='Мои папки, свернуто']")).isDisplayed()) {
            driver.findElement(By.cssSelector("[aria-label='Мои папки, свернуто']")).click();
        }
        WebElement testFolder = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href$='folder/9']")));
        testFolder.click();
        softAssertions.assertThat(listOfMail.getText()).contains("тема Тест");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-FromText")).getText())
                .contains("lvluphomework@yandex.ru");
        softAssertions.assertThat(
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Item_subject")))
                .getText()).contains("тема Тест");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_firstline")).getText())
                .contains("Текст письма Тест");
        WebElement avatar = wait
                .until(ExpectedConditions.elementToBeClickable(By.className("user-account")));
        avatar.click();
        WebElement logOut = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By.className("legouser__menu-item_action_exit")));
        logOut.click();
        softAssertions.assertAll();
    }

}
