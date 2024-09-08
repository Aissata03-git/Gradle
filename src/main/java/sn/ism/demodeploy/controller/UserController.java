package sn.ism.demodeploy.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ism.demodeploy.entity.User;
import sn.ism.demodeploy.service.UserService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService= userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.fetchUserById(id));
    }

    @GetMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

}
