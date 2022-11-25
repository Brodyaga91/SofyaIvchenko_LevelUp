package ru.levelp.at.homework6.go.rests.posts;

import com.github.javafaker.Faker;
import ru.levelp.at.homework6.go.rests.users.CreateUserData;

public class GenerationPost {
    static String title = new Faker().animal().toString();
    static String body = new Faker().elderScrolls().toString();
    public static int user_id = 103;


    static public CreatePostData createNewPost() {
        var createPostData = CreatePostData.builder()
                                           .title(title)
                                           .body(body)
                                           .userId(user_id)
                                           .build();
        return createPostData;
    }
}
