package ru.levelp.at.homework4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopUpMenuPage extends BasePage{

    @FindBy(className="legouser__menu-item_action_exit")
    private static WebElement logOut;

    public PopUpMenuPage(WebDriver driver) {
        super(driver);
    }

    public void logOut(){
        logOut.click();
    }

}
