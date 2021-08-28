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

    private String name;

    private String surname;

    private String email;

    private String password;

}
