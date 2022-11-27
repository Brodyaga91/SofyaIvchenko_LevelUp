package ru.levelp.at.homework6.go.rests.users;

import com.github.javafaker.Faker;

public class GenerationUser {
    static String email = new Faker().internet().emailAddress();
    static String name = new Faker().name().toString();
    public static String gender = "male";
    public static String status = "active";

    public static CreateUserData createNewUser() {
        return CreateUserData.builder()
                             .name(name)
                             .email(email)
                             .gender(gender)
                             .status(status)
                             .build();
    }

    public static CreateUserData createUserWithoutField(String name, String email, String gender, String status) {
        return CreateUserData.builder()
                             .name(name)
                             .email(email)
                             .gender(gender)
                             .status(status)
                             .build();
    }

   }
