package com.company.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("s3")
public class S3ConfigProperties {

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
