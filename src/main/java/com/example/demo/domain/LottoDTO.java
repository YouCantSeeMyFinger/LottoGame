package com.example.demo.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
public class LottoDTO {

    @Min(value = 1, message = "1이상 45이하의 숫자를 입력해주세요.")
    @Max(value = 45, message = "1이상 45이하의 숫자를 입력해주세요.")
    @NotNull(message = "반드시 값을 입력해주세요")
    private Integer LottoNumber1;

    @Range(min = 1, max = 45, message = "1이상 45이하의 숫자를 입력해주세요.")
    @NotNull
    private Integer LottoNumber2;

    @Range(min = 1, max = 45, message = "1이상 45이하의 숫자를 입력해주세요.")
    @NotNull
    private Integer LottoNumber3;
}
