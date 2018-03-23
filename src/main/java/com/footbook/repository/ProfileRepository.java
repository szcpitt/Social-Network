package com.footbook.repository;

import com.footbook.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile findById(Long id);

    @Modifying
    @Transactional
    @Query("update Profile p set p.firstName= ?1, p.lastName= ?2, p.gender= ?3 where p.userId= ?4")
    void setUserInfoById(String firstName,String lastName, String gender, Long userId);
}
