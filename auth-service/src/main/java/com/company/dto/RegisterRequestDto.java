package com.company.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.company.security.constants.AuthConstants.PASSWORD_MAX_LENGTH;
import static com.company.security.constants.AuthConstants.PASSWORD_MIN_LENGTH;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "password")
public class RegisterRequestDto {

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @Size(min = PASSWORD_MIN_LENGTH , max = PASSWORD_MAX_LENGTH)
    private String password;

}
