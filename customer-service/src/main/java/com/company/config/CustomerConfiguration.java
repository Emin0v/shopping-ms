package com.company.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.company")
@ComponentScan("com.company")
public class CustomerConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
