package com.footbook.web;

import com.footbook.model.Blog;
import com.footbook.model.Calendar;
import com.footbook.model.Profile;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.BlogService;
import com.footbook.service.CalendarService;
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
    private CalendarService calendarService;


    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, @ModelAttribute("blogMap") ArrayList<Map<String, String>> blogList) {
        model.addAttribute("newBlog",new Blog());
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        List<Long> friends=relationshipService.findByUser_id(userId);

        //Add friends' blog
        int i=0;
        for(Long friend_id:friends){
            List<String> friendBlogs=blogService.findBlogContentByUser_id(friend_id);
            Profile friendProfile=profileService.findById(friend_id);
            String friendName=friendProfile.getFirstName()+" "+friendProfile.getLastName();
            for(i=0;i<friendBlogs.size();i++){
                blogList.add(new HashMap<>());
                blogList.get(i).put(friendName,friendBlogs.get(i));
            }
        }

        //Add user's blog
        List<String> myBlogs=blogService.findBlogContentByUser_id(userId);
        Profile myProfile=profileService.findById(userId);
        if(myProfile!=null){
            String myName=myProfile.getFirstName()+" "+myProfile.getLastName();
            for(String myBlog:myBlogs){
                blogList.add(new HashMap<>());
                blogList.get(i).put(myName,myBlog);
                i++;
            }
        }
        model.addAttribute("blogList",blogList);


        // upcoming calendar
        int user_id=(int)(long)userId;
        List<Calendar> calendarList = calendarService.getAllCalendar(user_id);
        model.addAttribute("calendarList", calendarList);

        Calendar upcomingCalendar = calendarService.getUpcomingEvent(user_id);
        model.addAttribute("upcoming", upcomingCalendar);

        return "welcome";
    }

}
