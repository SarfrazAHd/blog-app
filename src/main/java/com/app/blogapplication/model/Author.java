package com.app.blogapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Post> posts;
}