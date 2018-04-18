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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

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
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value="/edit_profile",method=RequestMethod.GET)
    public String editProfile(Model model){
        model.addAttribute("profileForm",new Profile());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long id=user.getId();
        Profile profile=profileService.findById(id);
        if(profile!=null){
            if(profile.getImage()!=null){ model.addAttribute("myImage",profile.getImage()); }
            else{ model.addAttribute("myImage","/resources/img/avatar3.png"); }
        }else{
            model.addAttribute("myImage","/resources/img/avatar3.png");
        }

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
            if(profile.getImage()!=null){ model.addAttribute("myImage",profile.getImage()); }
            else{ model.addAttribute("myImage","/resources/img/avatar3.png"); }
        }else{
            model.addAttribute("myImage","/resources/img/avatar3.png");
        }
        return "profile";
    }

    @RequestMapping(value="/upLoadFile",method= RequestMethod.POST)
    public String upLoadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String contextPath=servletContext.getRealPath("/");
            User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            String fileName=user.getUsername()+"Image.png";
            String destinationPath=contextPath+"/resources/img/"+fileName;
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            File destination = new File(destinationPath);
            ImageIO.write(src, "png", destination);

            //Save image URL to DB
            profileService.setImageById("/resources/img/"+fileName);
        }
        return "redirect:/edit_profile";
    }

}
