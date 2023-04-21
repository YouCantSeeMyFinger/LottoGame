package com.example.demo.service;

import com.example.demo.domain.Lotto;
import com.example.demo.domain.LottoDTO;
import com.example.demo.repository.LottoRepoImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LottoService {
    private final LottoRepoImpl lottoRepo;

    public void save(LottoDTO lottoDTO) {
        lottoRepo.save(duplicated(lottoDTO));
    }

    public int createNumber() {
        Random random = new Random();
        return random.nextInt(45) + 1;
    }

    public Lotto duplicated(LottoDTO lottoDTO) {
        Lotto lotto = new Lotto();

        Set<Integer> set = new TreeSet<>();
        set.add(lottoDTO.getLottoNumber1());
        set.add(lottoDTO.getLottoNumber2());
        set.add(lottoDTO.getLottoNumber3());

        while (set.size() != 6) {
            set.add(createNumber());
        }

        Iterator<Integer> iterator = set.iterator();
        lotto.setLottoNumber1(iterator.next());
        lotto.setLottoNumber2(iterator.next());
        lotto.setLottoNumber3(iterator.next());
        lotto.setLottoNumber4(iterator.next());
        lotto.setLottoNumber5(iterator.next());
        lotto.setLottoNumber6(iterator.next());
        return lotto;
    }

    public void reset() {
        lottoRepo.reset();
    }

    public List<Lotto> findAll() {
        return lottoRepo.findAll();
    }

    public Lotto findId(Integer lottoKey) {
        return lottoRepo.findId(lottoKey);
    }
}
