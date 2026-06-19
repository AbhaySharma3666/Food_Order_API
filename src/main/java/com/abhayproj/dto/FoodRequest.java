package com.abhayproj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
    @NotBlank(message = "Food name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;
}
