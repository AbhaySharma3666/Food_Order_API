package com.abhayproj.service;

import com.abhayproj.io.UserRequest;
import com.abhayproj.io.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    String findByUserId();
}
