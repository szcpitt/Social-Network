package com.footbook.service;

import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.ProfileRepository;
import com.footbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void save(Profile profile){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userid=user.getId();
        profile.setUserId(userid);
        profile.setFirstName(profile.getFirstName());
        profile.setLastName(profile.getLastName());
        profile.setGender(profile.getGender());
        profileRepository.save(profile);
    }

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    public List<Profile> findAll(){
        return profileRepository.findAll();
    }
}
