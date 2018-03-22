package com.footbook.web;

import com.footbook.model.Profile;
import com.footbook.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value="/edit_profile",method=RequestMethod.GET)
    public String editProfile(Model model){
        model.addAttribute("profileForm",new Profile());
        return "edit_profile";
    }

    @RequestMapping(value="/profile",method= RequestMethod.POST)
    public String afterEdit(@ModelAttribute("profileForm") Profile profileForm){
        profileService.save(profileForm);
        return "profile";
    }

}
