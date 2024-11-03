package com.crio.xlido.services;

import java.util.List;
import com.crio.xlido.Repositories.UserRepository;
import com.crio.xlido.entities.User;

public class UserService {


    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public String createUser(List<String> tokens){
        String userEmail = tokens.get(1);
        String password = tokens.get(2);
        User user =  userRepository.save(new User(userEmail,password));
    //     return user.toString();
        return user.toString();
    }

    
}
