package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LottoDTO {
    // @NotBlank , @NotNull , @Size , @NotEmpty는 Primitive타입에 사용불가


    private Integer LottoNumber1;

    private Integer LottoNumber2;

    private Integer LottoNumber3;
}
