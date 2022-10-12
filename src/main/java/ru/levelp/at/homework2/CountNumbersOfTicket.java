package ru.levelp.at.homework2;


public class CountNumbersOfTicket {

    public boolean countNumbers(String numberOfTicket) {
        boolean result = false;
        try {
            char[] arrayOfChars = numberOfTicket.toCharArray();
            int[] arrayOfNumbers = new int[6];

            for (int i = 0; i < 6; i++) {
                arrayOfNumbers[i] = Character.getNumericValue(arrayOfChars[i]);
            }
            if (arrayOfNumbers[0] + arrayOfNumbers[1] + arrayOfNumbers[2] == arrayOfNumbers[3] + arrayOfNumbers[4]
                + arrayOfNumbers[5]) {
                result = true;
            }

        } finally {
            return result;
        }


    }
}
