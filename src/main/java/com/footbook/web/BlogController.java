package com.footbook.web;

import com.footbook.model.Blog;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BlogController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogService blogService;


    @RequestMapping(value="/addPost",method= RequestMethod.POST)
    public String post(@ModelAttribute("newBlog") Blog newBlog){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        newBlog.setUser_id(userId);
        blogService.save(newBlog);

        return "redirect:/welcome";
    }

}
