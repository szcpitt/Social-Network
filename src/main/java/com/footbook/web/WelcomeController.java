package com.footbook.web;

import com.footbook.model.Blog;
import com.footbook.model.Favorite;
import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.BlogService;
import com.footbook.service.FavoriteService;
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
    @Autowired
    private FavoriteService favoriteService;

    public ArrayList<List<String>> addBlog(ArrayList<List<String>> blogList,List<Blog> blogs,String name,Long id){
        for(Blog blog:blogs){
            int i=blogList.size();
            blogList.add(new ArrayList<>());
            blogList.get(i).add(Long.toString(blog.getId()));
            blogList.get(i).add(name);
            blogList.get(i).add(blog.getContent());
            Favorite favorite=favoriteService.findByBlogIdAndOwnerId(blog.getId(),id);
            if(favorite!=null){
                blogList.get(i).add("added");
            }else{
                blogList.get(i).add("notAdded");
            }
        }

        return blogList;
    }

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
            blogList=addBlog(blogList,friendBlogs,friendName,userId);
        }
        //Add user's blog
        List<Blog> myBlogs=blogService.findByUserId(userId);
        Profile myProfile=profileService.findById(userId);
        if(myProfile!=null){
            String myName=myProfile.getFirstName()+" "+myProfile.getLastName();
            blogList=addBlog(blogList,myBlogs,myName,userId);
        }
        model.addAttribute("blogList",blogList);
        return "welcome";
    }

}
