package com.example.demo.repository;

import com.example.demo.domain.Lotto;

import java.util.Collection;

public interface LottoRepo {

    void save(Lotto lotto);

    void reset();

    Collection<Lotto> findAll();

    Lotto findId(Integer lottoKey);

}
