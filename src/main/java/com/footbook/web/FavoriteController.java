package com.footbook.web;

import com.footbook.model.Blog;
import com.footbook.model.Favorite;
import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.service.BlogService;
import com.footbook.service.FavoriteService;
import com.footbook.service.ProfileService;
import com.footbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value="/addFavorite", method= RequestMethod.POST)
    public String addFavorite(String blogId){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long ownerId=user.getId();
        Favorite favorite=new Favorite(Long.valueOf(blogId),ownerId);
        favoriteService.save(favorite);
        return "redirect:/welcome";
    }

    @RequestMapping(value="/favorites",method=RequestMethod.GET)
    public String favorites(Model model, @ModelAttribute("blogList") ArrayList<List<String>> blogList){
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        List<Favorite> favorites=favoriteService.findByOwnerId(userId);
        for(Favorite favorite:favorites){
            int i=blogList.size();
            blogList.add(new ArrayList<>());
            Blog blog=blogService.findById(favorite.getBlogId());
            Profile friendProfile=profileService.findById(blog.getUserId());
            String friendName=friendProfile.getFirstName()+" "+friendProfile.getLastName();
            String profileImage=friendProfile.getImage();
            blogList.get(i).add(profileImage);
            blogList.get(i).add(friendName);
            blogList.get(i).add(blog.getContent());
            blogList.get(i).add(blog.getImage());
        }
        model.addAttribute(blogList);
        return "favorites";
    }
}
