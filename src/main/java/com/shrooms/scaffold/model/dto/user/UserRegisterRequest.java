package com.shrooms.scaffold.model.dto.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRegisterRequest {

    @NotBlank
    @Size(min = 6, message = "Username must be 6 or more characters")
    private String username;
    @NotBlank
    @Size(min = 6, message = "Password must be 6 or more characters")
    private String password;
    @NotBlank
    @Size(min = 6)
    private String confirmPassword;
    @NotBlank
    @Email(message = "Please enter valid email")
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;


}