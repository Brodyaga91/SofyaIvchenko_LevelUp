package ru.levelp.at.homework6.go.rests.posts;

import com.github.javafaker.Faker;

public class GenerationPost {
    static String title = new Faker().animal().toString();
    static String body = new Faker().elderScrolls().toString();
    public static int user_id = 103;


    public static CreatePostData createNewPost() {
        return CreatePostData.builder()
                             .title(title)
                             .body(body)
                             .userId(user_id)
                             .build();
    }

    public static CreatePostData createPostWithoutField(String title, String body, int userId) {
        return CreatePostData.builder()
                             .title(title)
                             .body(body)
                             .userId(userId)
                             .build();
    }
}
