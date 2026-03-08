package com.abhayproj.service;

import com.abhayproj.io.FoodRequest;
import com.abhayproj.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {

    String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest request, MultipartFile file);
}
