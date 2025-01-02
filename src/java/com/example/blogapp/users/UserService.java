package com.example.blogapp.users;
import com.example.blogapp.dtos.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public UserEntity getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    public UserEntity login(String username, String password){
        var user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));;

        if(user.getPassword()==password){
            return user;
        }
        return null;
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
