package com.footbook.service;

import com.footbook.model.Profile;

import java.util.List;

public interface ProfileService {

    void save(Profile profile);

    Profile findById(Long id);

    List<Profile> findAll();

    void setImageById(String image);
}
