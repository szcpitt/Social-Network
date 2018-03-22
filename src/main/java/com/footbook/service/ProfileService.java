package com.footbook.service;

import com.footbook.model.Profile;

public interface ProfileService {
    void save(Profile profile);

    Profile findById(Long id);
}
