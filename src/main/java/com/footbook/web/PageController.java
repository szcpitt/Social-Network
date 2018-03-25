package com.footbook.web;


import com.footbook.model.Profile;
import com.footbook.model.Relationship;
import com.footbook.model.User;
import com.footbook.service.ProfileService;
import com.footbook.service.RelationshipService;
import com.footbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;
    @Autowired
    private RelationshipService relationshipService;

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

    @RequestMapping(value="/favorites",method=RequestMethod.GET)
    public String favorites(){return "favorites";}

    @RequestMapping(value="/calendar",method=RequestMethod.GET)
    public String calendar(){return "calendar";}

    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public String profile(Model model){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long id=user.getId();
        Profile profile=profileService.findById(id);
        if(profile!=null){
            model.addAttribute("firstName",profile.getFirstName());
            model.addAttribute("lastName",profile.getLastName());
            model.addAttribute("gender",profile.getGender());
        }
        return "profile";
    }

}
