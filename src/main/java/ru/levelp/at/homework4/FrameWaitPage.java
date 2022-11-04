package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FrameWaitPage extends BasePage{
    @FindBy(className= "ComposeDoneScreen-Actions")
    private static WebElement closeFrame;

    public FrameWaitPage(WebDriver driver) {
        super(driver);
    }

    public void closeFrame(){
        wait.until(ExpectedConditions.visibilityOf(closeFrame)).click();
    }


}
