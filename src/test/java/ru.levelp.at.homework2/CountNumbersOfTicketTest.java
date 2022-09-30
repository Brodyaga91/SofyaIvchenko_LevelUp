package ru.levelp.at.homework2;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class CountNumbersOfTicketTest {
    private static final List<Integer> INPUT = List.of(1,2,3,1,2,3);

    @Test
    public void checkNumbers(){
        CountNumbersOfTicket testCheck = new CountNumbersOfTicket();
        boolean actualOutput = testCheck.countNumbers(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(true);

    }
}
