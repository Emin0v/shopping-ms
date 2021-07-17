package com.company.exception;

public class UserNotFoundException extends NotFoundException{

    private static final String USER_NOT_FOUND = "User not found";

     public UserNotFoundException(String username){
         super(String.format("User \"%s\" not found", username));
     }

     public UserNotFoundException(){
         super(USER_NOT_FOUND);
     }
}
