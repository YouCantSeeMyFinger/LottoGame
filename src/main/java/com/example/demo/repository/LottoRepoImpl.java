package com.example.demo.repository;

import com.example.demo.domain.Lotto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    @Override
    public List<Lotto> findAll() {
        return new ArrayList<>(repo.values());
    }


} // End Class
