package ru.levelp.at.homework6.go.rests.users;

import com.github.javafaker.Faker;
import ru.levelp.at.homework6.go.rests.TestData;

public class GenerationUser {
    static String email = new Faker().internet().emailAddress();
    static String name = new Faker().name().toString();
    public static String gender = "male";
    public static String status = "active";

    static public CreateUserData createNewUser() {
        var createUserData = CreateUserData.builder()
                                           .name(name)
                                           .email(email)
                                           .gender(gender)
                                           .status(status)
                                           .build();
        return createUserData;
    }
}
