package com.app.blogapplication.pojo;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long userId;
    private String name;
    private String email;
    private String password;
}