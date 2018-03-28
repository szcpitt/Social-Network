package com.footbook.service;

import com.footbook.model.Favorite;
import com.footbook.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService{

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> findByOwnerId(Long ownerId){
        return favoriteRepository.findByOwnerId(ownerId);
    }

    @Override
    public void save(Favorite favorite){
        favoriteRepository.save(favorite);
    }
}
