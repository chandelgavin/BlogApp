package com.example.blogapp.users;

import com.example.blogapp.dtos.CreateUserDTO;
//import lombok.var;
import com.example.blogapp.dtos.UserLoginDTO;
import com.example.blogapp.dtos.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    List<UserEntity> getAllUsers(){
       return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    UserEntity getUserById(@PathVariable("id") Long id){
        var user = userService.getUser(id);
        return user;
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> userSignUp(@RequestBody CreateUserDTO userDTO){
        UserEntity savedUser = userService.createUser(userDTO);
        URI savedUserURI = URI.create("/user/"+savedUser.getId());

        return ResponseEntity.created(savedUserURI).body(modelMapper.map(savedUser, UserResponseDTO.class));
    }


    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserLoginDTO loginDTO) {
        try {
            String loginMessage = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok(loginMessage);
        }
        catch (UserService.UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (UserService.WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
