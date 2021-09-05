package com.company.security.exception;

import com.company.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public static final String USER_NOT_FOUND = "User not found";

    public UserNotFoundException(String username) {
        super(String.format("User \"%s\" not found", username));
    }

    public UserNotFoundException(String param, String email) {
        super(String.format("User with \"%s\" - \"%s\" not found", param, email));
    }

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }

}
