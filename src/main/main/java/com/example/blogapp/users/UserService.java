package com.example.blogapp.users;
import com.example.blogapp.dtos.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserDTO userDTO){
        var user = UserEntity.builder().
                username(userDTO.getUsername()).
                password(userDTO.getPassword()).
                email(userDTO.getEmail()).
                build();

        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public UserEntity getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    public String login(String username, String password) throws WrongPasswordException{
            var user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));

        if(user.getPassword().equals(password)){
            return "Logged in successfully";
        }
        else throw new WrongPasswordException();
    }

    public static class WrongPasswordException extends IllegalArgumentException{
        public WrongPasswordException(){
            super("Incorrect Password");
        }
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username) {
            super("User with username "+username+" not found.");
        }
        public UserNotFoundException(Long userId) {
            super("User with user id "+userId+" not found.");
        }
    }

}
