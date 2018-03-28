package com.footbook.service;

import com.footbook.model.Relationship;
import com.footbook.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Override
    public void save(Relationship relationship){
        relationshipRepository.save(relationship);
    }

    @Override
    public List<Long> findByUser_id(Long user_id){
        return relationshipRepository.findByUser_id(user_id);
    }
}
