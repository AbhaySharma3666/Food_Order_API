package com.abhayproj.controller;

import com.abhayproj.dto.UserRequest;
import com.abhayproj.dto.UserResponse;
import com.abhayproj.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRequest request) {
        return userService.registerUser(request);
    }
}
