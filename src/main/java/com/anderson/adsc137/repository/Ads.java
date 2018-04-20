package com.anderson.adsc137.repository;

import com.anderson.adsc137.model.Ad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Ads extends CrudRepository<Ad, Long> {
    Ad findById(long id);

    List<Ad> findByUserId(long id);

    @Override
    void deleteById(Long aLong);
}
