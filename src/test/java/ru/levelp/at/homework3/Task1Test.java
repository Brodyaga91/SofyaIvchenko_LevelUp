package ru.levelp.at.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;



public class Task1Test {

    private static final String URL_MAIL = "https://dzen.ru/";

    private WebDriver driver;
    private WebDriverWait wait;
    private final SoftAssertions SOFT_ASSERTIONS = new SoftAssertions();

   @BeforeClass
   public void activateDriver() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       wait = new WebDriverWait(driver, Duration.ofMillis(10000));
   }


    @Test
    public void loginMail()  {
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
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        assertThat(driver.getTitle()).contains("Входящие - Яндекс Почта");

    }

    @Test (priority = 1)
    public void createAndSaveDraft(){
        WebElement writeMail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("qa-LeftColumn-ComposeButton")));
        writeMail.click();
        WebElement destination = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("composeYabbles")));
        destination.sendKeys("lvluphomework@yandex.ru");
        WebElement subject = driver.findElement(By.className("composeTextField"));
        subject.sendKeys("Тема письма");
        WebElement textField = driver.findElement(By.className("cke_wysiwyg_div"));
        textField.click();
        textField.sendKeys("Текст письма");
        WebElement buttonClose = driver.findElement(By.className("qa-ControlButton_button_close"));
        buttonClose.click();
        WebElement draftFolder = driver.findElement(By.cssSelector("[href$='draft']"));
        draftFolder.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span [title='Тема письма']")));
        assertThat(driver.findElement(By.tagName("body")).getText()).contains("Тема письма");
    }

    @Test (priority = 2)
    public void assertArtefacts(){
        SOFT_ASSERTIONS.assertThat(driver.findElement(By.className("mail-MessageSnippet-FromText")).getText()).contains("lvluphomework@yandex.ru");
        SOFT_ASSERTIONS.assertThat(driver.findElement(By.className("mail-MessageSnippet-Item_subject")).getText()).contains("Тема письма");
        SOFT_ASSERTIONS.assertThat(driver.findElement(By.className("mail-MessageSnippet-Item_firstline")).getText()).contains("Текст письма");
        SOFT_ASSERTIONS.assertAll();
    }

    @Test (priority = 3)
    public void sendDraft(){
        WebElement myMail = driver.findElement(By.className("mail-MessageSnippet-Item_subject"));
        myMail.click();
        WebElement sendMail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeSendButton")));
        sendMail.click();
        WebElement closeFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ComposeDoneScreen-Actions")));
        closeFrame.click();
        WebElement draftFolder = driver.findElement(By.cssSelector("[href$='draft']"));
        draftFolder.click();
        SOFT_ASSERTIONS.assertThat(driver.findElement(By.className("Text_typography_subheader-l")).getText()).contains("В папке «Черновики» нет писем");
        WebElement sentFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href$='sent']")));
        sentFolder.click();
        driver.navigate().refresh();
        WebElement listOfSent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mail-MessageSnippet-Content")));
        SOFT_ASSERTIONS.assertThat(listOfSent.getText()).contains("Тема письма");
        SOFT_ASSERTIONS.assertAll();

    }

    @AfterClass
    public void finish(){
        WebElement avatar = driver.findElement(By.className("user-account"));
        avatar.click();
        WebElement logOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("legouser__menu-item_action_exit")));
        logOut.click();
        driver.quit();
    }
}


