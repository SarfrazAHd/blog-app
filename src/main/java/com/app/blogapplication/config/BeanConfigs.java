package com.app.blogapplication.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
public class BeanConfigs {
    @Bean
    ModelMapper modelMapper (){
        return new ModelMapper();
    }

}
