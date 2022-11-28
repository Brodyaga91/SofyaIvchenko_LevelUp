package ru.levelp.at.homework6.go.rests.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostData {
    @JsonProperty("user_id")
    private int userId;
    private String title;
    private String body;

}
