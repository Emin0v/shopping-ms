package com.company.entity;


import com.company.security.constants.UserAuthority;
import com.company.security.constants.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.company.security.constants.AuthConstants.PASSWORD_MIN_LENGTH;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Builder
@Table(name = User.TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
public class User  extends AbstractAuditingEntity {

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = PASSWORD_MIN_LENGTH , max = 190)
    private String password;

    private UserAuthority authority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
    }

}