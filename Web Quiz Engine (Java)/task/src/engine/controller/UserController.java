package engine.controller;

import engine.dto.UserDto;
import engine.entity.User;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/register")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok().build();
    }
}
