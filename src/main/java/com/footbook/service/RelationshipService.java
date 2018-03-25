package com.footbook.service;

import com.footbook.model.Relationship;

import java.util.List;

public interface RelationshipService {
    void save(Relationship relationship);

    List<Long> findByUser_id(Long user_id);
}
