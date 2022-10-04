package ru.levelp.at.homework2;

import java.util.List;
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
            {List.of(1, 2, 3, 4, 5, 6), false},
            {List.of(1, 2, 3, 1, 2, 3), true},
            {List.of(0, 0, 0, 0, 0, 0), true}
        };
    }

    @DataProvider
    public Object[][] checkHappyDataProviderNegative() {
        return new Object[][] {
            {List.of(1, 2, 3, 4, 5, 6, 7), false},
            {List.of(1, 2, 3, 1, 2), false},
            {List.of(-1, -2, -3, -1, -2, -3), false},
            {List.of("a", "a", "a", "a", "a", "a"), false},
            {null, false}
        };
    }

    @Test(dataProvider = "checkHappyDataProvider")
    public void inputNumbers(List<Integer> input, boolean result) {
        boolean actualOutput = testCheck.countNumbers(input);
        Assertions.assertThat(actualOutput).isEqualTo(result);
    }

    @Test(dataProvider = "checkHappyDataProviderNegative")
    public void inputNumbersBad(List<Integer> input, boolean result) {
        boolean actualOutput = testCheck.countNumbers(input);
        Assertions.assertThat(actualOutput).isEqualTo(result);
    }
}



