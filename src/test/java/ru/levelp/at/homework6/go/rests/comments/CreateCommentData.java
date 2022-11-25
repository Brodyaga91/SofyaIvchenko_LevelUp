package ru.levelp.at.homework6.go.rests.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentData {
    @JsonProperty("post_id")
    private int postId;
    private String name;
    private String body;
    private String email;
}
