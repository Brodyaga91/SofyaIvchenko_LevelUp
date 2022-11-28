package ru.levelp.at.homework6.go.rests.comments;

import com.github.javafaker.Faker;

public class GenerationComment {
    static String name = new Faker().funnyName().toString();
    static String body = new Faker().elderScrolls().toString();
    static String email = new Faker().internet().emailAddress();
    public static int post_id = 103;

    public static CreateCommentData createNewComment() {
        return CreateCommentData.builder()
                                .postId(post_id)
                                .body(body)
                                .name(name)
                                .email(email)
                                .build();
    }

    public static CreateCommentData createCommentWithoutField(int postId, String body, String name, String email) {
        return CreateCommentData.builder()
                                .postId(postId)
                                .body(body)
                                .name(name)
                                .email(email)
                                .build();
    }
}
