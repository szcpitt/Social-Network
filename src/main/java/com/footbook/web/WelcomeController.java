package com.footbook.web;

import com.footbook.model.Blog;
import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.BlogService;
import com.footbook.service.ProfileService;
import com.footbook.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogService blogService;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private ProfileService profileService;


    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, @ModelAttribute("blogMap") ArrayList<List<String>> blogList) {
        model.addAttribute("newBlog",new Blog());
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        List<Long> friends=relationshipService.findByUser_id(userId);

        //Add friends' blog
        for(Long friend_id:friends){
            List<Blog> friendBlogs=blogService.findByUserId(friend_id);
            Profile friendProfile=profileService.findById(friend_id);
            String friendName=friendProfile.getFirstName()+" "+friendProfile.getLastName();
            for(Blog friendBlog:friendBlogs){
                int i=blogList.size();
                blogList.add(new ArrayList<>());
                blogList.get(i).add(Long.toString(friendBlog.getId()));
                blogList.get(i).add(friendName);
                blogList.get(i).add(friendBlog.getContent());
            }
        }

        //Add user's blog
        List<Blog> myBlogs=blogService.findByUserId(userId);
        Profile myProfile=profileService.findById(userId);
        if(myProfile!=null){
            String myName=myProfile.getFirstName()+" "+myProfile.getLastName();
            for(Blog myBlog:myBlogs){
                int j=blogList.size();
                blogList.add(new ArrayList<>());
                blogList.get(j).add(Long.toString(myBlog.getId()));
                blogList.get(j).add(myName);
                blogList.get(j).add(myBlog.getContent());

            }
        }
        model.addAttribute("blogList",blogList);
        return "welcome";
    }

}
