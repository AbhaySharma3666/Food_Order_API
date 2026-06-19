package com.abhayproj.service;

import com.abhayproj.dto.UserRequest;
import com.abhayproj.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    String findByUserId();
}
