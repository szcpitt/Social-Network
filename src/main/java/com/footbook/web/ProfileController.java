package com.footbook.web;

import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.ProfileRepository;
import com.footbook.repository.UserRepository;
import com.footbook.service.ProfileService;
import com.footbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/edit_profile",method=RequestMethod.GET)
    public String editProfile(Model model){
        model.addAttribute("profileForm",new Profile());
        return "edit_profile";
    }

    @RequestMapping(value="/profile",method= RequestMethod.POST)
    public String afterEdit(@ModelAttribute("profileForm") Profile profileForm){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        if(profileRepository.findById(userId)!=null){
            profileRepository.setUserInfoById(profileForm.getFirstName(),profileForm.getLastName(),profileForm.getGender(),userId);
        }else{
            profileService.save(profileForm);
        }
        return "redirect:/profile";
    }

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
