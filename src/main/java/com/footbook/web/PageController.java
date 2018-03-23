package com.footbook.web;

import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.ProfileRepository;
import com.footbook.repository.UserRepository;
import com.footbook.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(value="/friends",method= RequestMethod.GET)
    public String friends(@ModelAttribute("peopleMap") HashMap<Long, String> peopleMap){
        List<Profile> peopleList=profileRepository.findAll();
        for(int i=0;i<peopleList.size();i++){
            peopleMap.put(peopleList.get(i).getId(),peopleList.get(i).getFirstName()+" "+peopleList.get(i).getLastName());
        }
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        peopleMap.remove(userId);
        return "friends";
    }

    @RequestMapping(value="/favorites",method=RequestMethod.GET)
    public String favorites(){return "favorites";}

    @RequestMapping(value="/calendar",method=RequestMethod.GET)
    public String calendar(){return "calendar";}

    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public String profile(Model model){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
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
