package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Member {

    private Integer Id;


    @NotBlank
    private String loginId;

    @NotBlank
    private String loginPassword;

    @NotBlank
    private String name;
}
