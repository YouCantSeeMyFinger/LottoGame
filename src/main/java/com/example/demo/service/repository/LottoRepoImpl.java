package com.example.demo.service.repository;

import com.example.demo.domain.Lotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class LottoRepoImpl implements LottoRepo {


    private static final Map<Integer, Lotto> repo = new HashMap<>();
    private static int sequence = 0;

    @Override
    public void save(Lotto lotto) {
        sequence++;
        repo.put(sequence, lotto);
        log.info("repo => {}", repo);
    }

    @Override
    public void reset() {
        repo.clear();
        log.info("repo => {}", repo);
    }


} // End Class
