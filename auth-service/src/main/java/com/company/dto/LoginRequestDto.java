package com.company.dto;

import lombok.*;

import javax.validation.constraints.*;

import static com.company.security.constants.AuthConstants.PASSWORD_MAX_LENGTH;
import static com.company.security.constants.AuthConstants.PASSWORD_MIN_LENGTH;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Builder
public class LoginRequestDto {

    @Email
    @NotBlank
    private String email;

    @Size(min = PASSWORD_MIN_LENGTH , max = PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    private Boolean rememberMe;

}
