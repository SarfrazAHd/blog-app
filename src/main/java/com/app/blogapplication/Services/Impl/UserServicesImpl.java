package com.app.blogapplication.Services.Impl;

import com.app.blogapplication.Services.UserServices;
import com.app.blogapplication.model.Author;
import com.app.blogapplication.pojo.AuthorDTO;
import com.app.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepo;
    private final ModelMapper mapper;

    @Override
    public AuthorDTO createUser(AuthorDTO userdto) {
        try {
            Author user = this.mapper.map(userdto, Author.class);
            userRepo.save(user);
            log.info("user created");
            return userdto;
        } catch (Exception e) {
            log.error("Something went wrong while creating user profile");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AuthorDTO> getALlusers() {
        try {
            List<AuthorDTO> users = userRepo.findAll().stream()
                    .map(user -> this.mapper.map(user, AuthorDTO.class))
                    .collect(Collectors.toList());
            return users;
        } catch (Exception e) {
            log.error("Something went wrong while fetching users..");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public AuthorDTO getUserById(Long userId) {
        try {
            AuthorDTO users = this.mapper.map(userRepo.findById(userId), AuthorDTO.class);
            return users;
        } catch (Exception e) {
            log.error("Something went wrong while fetching user for Id {}", userId);
            throw new RuntimeException(e.getMessage());
        }
    }
}
