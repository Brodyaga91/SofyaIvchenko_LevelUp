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


public class Task2Test {

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
    public void sendMailCheckSentAndFolder() {
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
        softAssertions.assertAll();
    }

    @Test(priority = 2)
    public void assertArtefacts() {
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-FromText")).getText())
                .contains("lvluphomework@yandex.ru");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_subject")).getText())
                .contains("тема Тест");
        softAssertions.assertThat(
                driver.findElement(By.className("mail-MessageSnippet-Item_firstline")).getText())
                .contains("Текст письма Тест");
        softAssertions.assertAll();
    }

    @AfterClass
    public void finish() {
        WebElement avatar = wait
                .until(ExpectedConditions.elementToBeClickable(By.className("user-account")));
        avatar.click();
        WebElement logOut = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("legouser__menu-item_action_exit")));
        logOut.click();
        driver.quit();
    }

}
