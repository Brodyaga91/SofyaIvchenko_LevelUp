package ru.levelp.at.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    private static final String URL_MAIL = "https://dzen.ru/";

    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssertions SOFT_ASSERTIONS = new SoftAssertions();

    @BeforeClass
    public void activateDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }


    @Test
    public void loginMail() throws InterruptedException {
        driver.navigate().to(URL_MAIL);
        WebElement buttonLoginMainPage = driver.findElement(By.className("dzen-header-desktop__isUnauthorized-2e"));
        buttonLoginMainPage.click();
        WebElement typeLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-type='login']")));
        typeLogin.click();
        WebElement loginTextBox = driver.findElement(By.id("passp-field-login"));
        loginTextBox.sendKeys("lvluphomework");
        WebElement buttonLogin = driver.findElement(By.id("passp:sign-in"));
        buttonLogin.click();
        WebElement passwordTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
        passwordTextBox.sendKeys("lvluphomework123");
        WebElement buttonLoginRefresh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Button2_type_submit")));
        buttonLoginRefresh.click();
        WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dzen-search-arrow-common")));
        searchTextBox.click();
        WebElement iFrame = driver.findElement(By.cssSelector("iframe.dzen-search-arrow-common__frame"));
        driver.switchTo().frame(iFrame);
        WebElement buttonMail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-link2")));
        buttonMail.click();
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        assertThat(driver.getTitle().equals("Входящие - Яндекс Почта"));

    }

    @Test(priority = 1)
    public void sendMail(){
        WebElement writeMail = driver.findElement(By.className("qa-LeftColumn-ComposeButton"));
        writeMail.click();
        WebElement destination = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("composeYabbles")));
        destination.sendKeys("lvluphomework@yandex.ru");
        WebElement subject = driver.findElement(By.className("composeTextField"));
        subject.sendKeys("Тест");
        WebElement textField = driver.findElement(By.className("cke_wysiwyg_div"));
        textField.click();
        textField.sendKeys("Текст письма Тест");
        WebElement sendMail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeSendButton")));
        sendMail.click();
        WebElement closeFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeDoneScreen-Actions")));
        closeFrame.click();
        WebElement sentFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href$='sent']")));
        sentFolder.click();
        SOFT_ASSERTIONS.assertThat(driver.findElement(By.tagName("body")).getText().contains("Тест"));
        WebElement myFolders = driver.findElement(By.className("qa-LeftColumn-FolderExpander"));
        myFolders.click();
        WebElement testFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[aria-label='Тест, папка']")));
        testFolder.click();
        SOFT_ASSERTIONS.assertThat(driver.findElement(By.tagName("body")).getText().contains("Тест"));
    }
}
