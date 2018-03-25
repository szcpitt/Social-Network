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

import java.util.HashMap;
import java.util.List;

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
    public String welcome(Model model, @ModelAttribute("blogMap") HashMap<String, String> blogMap) {
        model.addAttribute("newBlog",new Blog());
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        List<Long> friends=relationshipService.findByUser_id(userId);

        //Add friends' blog
        for(Long friend_id:friends){
            List<String> friendBlogs=blogService.findBlogContentByUser_id(friend_id);
            Profile friendProfile=profileService.findById(friend_id);
            String friendName=friendProfile.getFirstName()+" "+friendProfile.getLastName();
            for(String friend_blog:friendBlogs){
                blogMap.put(friendName,friend_blog);
            }
        }

        //Add user's blog
        List<String> myBlogs=blogService.findBlogContentByUser_id(userId);
        Profile myProfile=profileService.findById(userId);
        if(myProfile!=null){
            String myName=myProfile.getFirstName()+" "+myProfile.getLastName();
            for(String myBlog:myBlogs){
                String content=myBlog;
                blogMap.put(myName,content);
            }
            model.addAllAttributes(blogMap);
        }
        return "welcome";
    }

}
