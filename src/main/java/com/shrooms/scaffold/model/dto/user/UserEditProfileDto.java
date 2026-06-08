package com.shrooms.scaffold.model.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditProfileDto {
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
}
