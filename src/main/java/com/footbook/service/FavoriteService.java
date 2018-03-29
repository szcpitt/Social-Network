package com.footbook.service;

import com.footbook.model.Favorite;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findByOwnerId(Long ownerId);

    void save(Favorite favorite);

    Favorite findByBlogIdAndOwnerId(Long blogId,Long ownerId);
}
