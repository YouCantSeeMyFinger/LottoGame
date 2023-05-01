package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Member {


    public Member(String loginId, String loginPassword, String name) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
    }

    private Integer Id;


    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z0-9[^\\p{Punct}]]{1,10}$", message = "사용할 수 없는 아이디입니다.")
    private String loginId;

    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=\\S{8,15})(?!.*([a-zA-Z0-9])\\1{1})(?=[A-Z])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,15}$", message = "입력값이 조건에 맞지 않습니다.")
    @NotBlank
    private String loginPassword;

    @NotBlank
    @Pattern(regexp = "^([가-힣]{2,5}|[a-zA-Z]{10,20})$", message = "사용할 수 없는 이름입니다.")
    private String name;

    public Member() {

    }
}
