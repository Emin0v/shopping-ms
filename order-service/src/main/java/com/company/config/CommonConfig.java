package com.company.config;

import com.company.security.auth.service.SecurityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityService.class})
public class CommonConfig {
}
