package ru.levelp.at.homework2;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CountNumbersOfTicketTest {
    private CountNumbersOfTicket testCheck;

    @BeforeMethod
    public void beforeMethod() {
        testCheck = new CountNumbersOfTicket();
    }

    @DataProvider
    public Object[][] checkHappyDataProvider() {
        return new Object[][] {
            {"123456", false},
            {"123123", true},
            {"000000", true}
        };
    }

    @DataProvider
    public Object[][] checkHappyDataProviderNegative() {
        return new Object[][] {
            {"1234567", false},
            {"12312", false},
            {"-1-2-3-1-2-3)", false},
            {"jsdfls", false},
            {null, false}
        };
    }

    @Test(dataProvider = "checkHappyDataProvider")
    public void inputNumbers(String input, boolean result) {
        boolean actualOutput = testCheck.countNumbers(input);
        Assertions.assertThat(actualOutput).isEqualTo(result);
    }

    @Test(dataProvider = "checkHappyDataProviderNegative")
    public void inputNumbersBad(String input, boolean result) {
        boolean actualOutput = testCheck.countNumbers(input);
        Assertions.assertThat(actualOutput).isEqualTo(result);
    }
}



