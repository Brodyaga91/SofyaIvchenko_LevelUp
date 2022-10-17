package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.ArrayList;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Task3Test {
    private static final String URL_MAIL = "https://dzen.ru/";

    private WebDriver driver;
    private WebDriverWait wait;
    private final SoftAssertions softAssertions = new SoftAssertions();

    @BeforeClass
    public void activateDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }


    @Test
    public void loginMail() {
        driver.navigate().to(URL_MAIL);
        WebElement buttonLoginMainPage = driver.findElement(By.className("dzen-header-desktop__isUnauthorized-2e"));
        buttonLoginMainPage.click();
        WebElement typeLogin = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-type='login']")));
        typeLogin.click();
        WebElement loginTextBox = driver.findElement(By.id("passp-field-login"));
        loginTextBox.sendKeys("lvluphomework");
        WebElement buttonLogin = driver.findElement(By.id("passp:sign-in"));
        buttonLogin.click();
        WebElement passwordTextBox = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
        passwordTextBox.sendKeys("lvluphomework123");
        WebElement buttonLoginRefresh = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Button2_type_submit")));
        buttonLoginRefresh.click();
        WebElement searchTextBox = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("dzen-search-arrow-common")));
        searchTextBox.click();
        WebElement frame = driver.findElement(By.cssSelector("iframe.dzen-search-arrow-common__frame"));
        driver.switchTo().frame(frame);
        WebElement buttonMail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-link2")));
        buttonMail.click();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        assertThat(driver.getTitle()).contains("Яндекс Почта");

    }

    @Test(priority = 1)
    public void sendMailCheckInbox() {
        WebElement writeMail = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("qa-LeftColumn-ComposeButton")));
        writeMail.click();
        WebElement destination = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("composeYabbles")));
        destination.sendKeys("lvluphomework@yandex.ru");
        WebElement subject = driver.findElement(By.className("composeTextField"));
        subject.sendKeys("тема Задание 3");
        WebElement textField = driver.findElement(By.className("cke_wysiwyg_div"));
        textField.click();
        textField.sendKeys("Текст письма Задание 3");
        WebElement sendMail = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeSendButton")));
        sendMail.click();
        WebElement closeFrame = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeDoneScreen-Actions")));
        closeFrame.click();
        driver.navigate().refresh();
        WebElement listOfSent = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Content")));
        assertThat(listOfSent.getText()).contains("тема Задание 3");
    }

    @Test(priority = 2)
    public void assertArtefacts() {
        softAssertions.assertThat(
                driver.findElement(By.cssSelector("span[title='lvluphomework@yandex.ru']")).getAttribute("title"))
                .isEqualTo("lvluphomework@yandex.ru");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_subject")).getText())
                .contains("тема Задание 3");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_firstline")).getText())
                .contains("Текст письма Задание 3");
        softAssertions.assertAll();
    }

    @Test(priority = 3)
    public void deleteAndCheck() {
        WebElement checkBox = driver.findElement(By.className("_nb-checkbox-normal-flag"));
        checkBox.click();
        WebElement buttonDelete = wait
                .until(ExpectedConditions.elementToBeClickable(By.className("ns-view-toolbar-button-delete")));
        buttonDelete.click();
        WebElement folderTrash = driver.findElement(By.cssSelector("[href$='trash']"));
        folderTrash.click();
        WebElement listOfSent = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Content")));
        assertThat(listOfSent.getText()).contains("тема Задание 3");
    
    }

    @AfterClass
    public void finish() {
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(By.className("user-account")));
        avatar.click();
        WebElement logOut = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("legouser__menu-item_action_exit")));
        logOut.click();
        driver.quit();
    }
}
