package ru.levelp.at.homework6.go.rests.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserData {
    private String name;
    private String email;
    private String gender;
    private String status;

}
