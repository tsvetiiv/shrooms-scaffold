package com.shrooms.scaffold.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditProfileDto {
    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;
    @Size(max=500, message = "Profile picture URL must be up to 500 characters")
    private String profilePicture;
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;
}
