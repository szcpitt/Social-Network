package com.footbook.web;

import com.footbook.model.Profile;
import com.footbook.model.Relationship;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.ProfileService;
import com.footbook.service.RelationshipService;
import com.footbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;


@Controller
public class FriendsController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/addFriends", method= RequestMethod.POST)
    public String addFriend(String friend_id){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        Long friend=Long.valueOf(friend_id);
        relationshipService.save(new Relationship(userId,friend));
        relationshipService.save(new Relationship(friend,userId));
        return "redirect:/friends";
    }

    @RequestMapping(value="/friends" ,method= RequestMethod.GET)
    public String friends(@ModelAttribute("peopleMap") HashMap<Long, String> peopleMap,
                          @ModelAttribute("friendsMap") HashMap<Long, String> friendsMap){
        List<Profile> peopleList=profileService.findAll();
        for(int i=0;i<peopleList.size();i++){
            peopleMap.put(peopleList.get(i).getUserId(),peopleList.get(i).getFirstName()+" "+peopleList.get(i).getLastName());
        }
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        peopleMap.remove(userId);
        List<Long> friends=relationshipService.findByUser_id(userId);

        if(friends!=null){
            for(Long friend:friends){
                peopleMap.remove(friend);
                Profile friendProfile=profileService.findById(friend);
                friendsMap.put(friend,friendProfile.getFirstName()+" "+friendProfile.getLastName());
            }
        }
        return "friends";
    }
}
