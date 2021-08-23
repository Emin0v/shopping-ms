package com.company.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Builder
public class RegisterDTO {

    @NotEmpty(message = "*Please provide your first name")
    private String name;

    private String surname;

    @Email(message = "*Please provide a valid email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @NotEmpty(message = "*Please provide your password")
    private String password;

}
