package ru.levelp.at.homework2;

import java.util.List;

public class CountNumbersOfTicket {

    public boolean countNumbers(List<Integer> number){
        boolean result = false;
        try {
            if (number.get(0) + number.get(1) + number.get(2) == number.get(3) + number.get(4) + number.get(5)) {
                result = true;
            }
        } finally {
            return result;
        }


    }
}