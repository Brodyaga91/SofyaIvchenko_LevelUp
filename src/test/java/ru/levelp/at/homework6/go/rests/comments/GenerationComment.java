package ru.levelp.at.homework6.go.rests.comments;

import com.github.javafaker.Faker;
import ru.levelp.at.homework6.go.rests.posts.CreatePostData;

public class GenerationComment {
    static String name = new Faker().funnyName().toString();
    static String body = new Faker().elderScrolls().toString();
    static String email = new Faker().internet().emailAddress();
    public static int post_id = 103;

    static public CreateCommentData createNewComment() {
        var createCommentData = CreateCommentData.builder()
                                           .postId(post_id)
                                           .body(body)
                                           .name(name)
                                           .email(email)
                                           .build();
        return createCommentData;
    }
}
