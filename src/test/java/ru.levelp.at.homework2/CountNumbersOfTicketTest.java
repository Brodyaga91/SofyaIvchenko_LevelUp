package ru.levelp.at.homework2;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;

public class CountNumbersOfTicketTest {
    private static final List<Integer> INPUT = List.of(1,2,3,1,2,3);
    private static final Boolean RESULT = true;
    private CountNumbersOfTicket testCheck;

    @BeforeMethod
    public void beforeMethod(){
        testCheck = new CountNumbersOfTicket();
    }

    @Test
    //проверка любых успешных с разными корректным входными данными (a=b, a!=b, 0)
    public void inputNumbersPositive(){
        boolean actualOutput = testCheck.countNumbers(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(RESULT);

    }

    @Test
    public void inputNullNegative(){
        boolean actualOutput = testCheck.countNumbers(null);
        Assertions.assertThat(actualOutput).isEqualTo(RESULT);

    }

    @Test
    public void inputNotFullNumbersNegative(){
        boolean actualOutput = testCheck.countNumbers(null);
        Assertions.assertThat(actualOutput).isEqualTo(RESULT);

    }

}
