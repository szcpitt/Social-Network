package com.footbook.web;

import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/friends",method= RequestMethod.GET)
    public String friends(){return "friends";}

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
