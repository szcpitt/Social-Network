package com.footbook.repository;

import com.footbook.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query("select r.friend_id from Relationship r where r.user_id= ?1")
    List<Long> findByUser_id(Long user_id);
}
