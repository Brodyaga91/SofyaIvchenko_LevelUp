package ru.levelp.at.homework6.go.rests.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostData {

        private int user_id;
        private String title;
        private String body;

    }


