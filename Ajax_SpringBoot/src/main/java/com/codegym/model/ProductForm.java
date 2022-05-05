package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {

    @NotEmpty(message = "Không được phép để trống")
    @Size(min = 5, max = 20, message = "Tên sản phẩm phải từ 5 -> 20 ký tự")
    private Long id;

    private String name;
    @NotNull
    private double price;

    @NotBlank
    private String description;

    private MultipartFile image;

    private Category category;
}
