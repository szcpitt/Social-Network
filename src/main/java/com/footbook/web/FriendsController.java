package com.footbook.web;

import com.footbook.model.Profile;
import com.footbook.model.Relationship;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.ProfileService;
import com.footbook.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FriendsController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value="/addFriends", method= RequestMethod.POST)
    public String addFriend(String friend_id){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        Long friend=Long.valueOf(friend_id);
//        Profile profile=profileService.findById(userId);
//        Profile profile2=profileService.findById(friend);
//        profile.getFriends().add(new Relationship(userId,friend));
//        profile2.getFriends().add(new Relationship(friend,userId));
        relationshipService.save(new Relationship(userId,friend));
        relationshipService.save(new Relationship(friend,userId));
        return "redirect:/friends";
    }
}
