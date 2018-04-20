package com.anderson.adsc137.service;

import com.anderson.adsc137.model.Ad;
import com.anderson.adsc137.repository.Ads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    private final Ads ads;


    @Autowired
    public AdService(Ads ads) {
        this.ads = ads;
    }

    public Ad findById(long id) {
        return ads.findById(id);
    }

    public Iterable<Ad> findAll() {
        return ads.findAll();
    }

    public Ad saveAd(Ad ad) {
        return ads.save(ad);
    }

    public List<Ad> findByUserId(long id) {
        return ads.findByUserId(id);
    }

    public void delete(long id) {
        ads.deleteById(id);
    }

}
