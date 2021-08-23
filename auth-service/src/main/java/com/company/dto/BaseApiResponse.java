package com.company.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BaseApiResponse {

    private String message;
    private Integer status;

}
