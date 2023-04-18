package com.example.demo.service.repository;

import com.example.demo.domain.Lotto;

public interface LottoRepo {

    void save(Lotto lotto);

    void reset();

}
