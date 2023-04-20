package com.example.demo.domain;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
public class LottoDTO {

    @NotNull(message = "반드시 숫자를 입력해주세요.")
    @Range(min = 1, max = 45)
    private Integer lottoNumber1;

    @NotNull(message = "반드시 숫자를 입력해주세요.")
    @Range(min = 1, max = 45)
    private Integer lottoNumber2;

    @NotNull(message = "반드시 숫자를 입력해주세요.")
    @Range(min = 1, max = 45)
    private Integer lottoNumber3;
}
