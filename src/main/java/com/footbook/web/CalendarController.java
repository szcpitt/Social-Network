package com.footbook.web;

import com.footbook.model.Calendar;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.CalendarService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService calendarService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String listBlog(ModelAndView model){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long user_id=user.getId();
        List<Calendar>  calendarList=calendarService.findByUserId(user_id);
        String calListJson = new Gson().toJson(calendarList);
        model.addObject("calendarList", calListJson);
        model.setViewName("calendar");
        return "calendar";
    }
}
