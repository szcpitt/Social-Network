package com.footbook.web;

import com.footbook.model.Calendar;
import com.footbook.model.SmallCalendar;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.CalendarService;
import com.footbook.util.Calendar2SmallCalendar;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService CalendarService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Calendar2SmallCalendar calendar2SmallCalendar;

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public ModelAndView listBlog(ModelAndView model){

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long user_id=user.getId();
        int userId = (int)(long) user_id;

        // add a new empty calendar
        model.addObject("newCalendar", new SmallCalendar());

        // calendar
        String user1 = user.toString();
        model.addObject("user_id", user1);
        model.setViewName("calendar");

        List<Calendar> CalendarList = CalendarService.getAllCalendar(userId);

        // convert Calender to small calendar
        if (CalendarList.size() == 1){
            SmallCalendar smallCalendar = calendar2SmallCalendar.convertOne(CalendarList.get(0));
            String smallString = new Gson().toJson(smallCalendar);
            String smallString2 = "[" + smallString + "]";
            model.addObject("calendarList", smallString2);
            return model;
        } else if (CalendarList.size() > 1){
            List<SmallCalendar> SmallList = calendar2SmallCalendar.ConvertAll(CalendarList);
            String smallString = new Gson().toJson(SmallList);
            model.addObject("calendarList", smallString);
            return model;
        } else {
            model.addObject("calendarList", "");
            return model;
        }

    }

    @RequestMapping(value = "/calendar", method = RequestMethod.POST)
    public String addCalendar(@ModelAttribute("newCalendar")SmallCalendar smallCalendar){
        Calendar newCalendar = new Calendar();
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long user_id=user.getId();
        int userId = (int)(long) user_id;

        String title = smallCalendar.getTitle();
        String start = smallCalendar.getStart();
        String end = smallCalendar.getEnd();

        newCalendar.setUserId(userId);
        newCalendar.setTitle(title);
        newCalendar.setStart(start);
        newCalendar.setEnd(end);

        CalendarService.addCalendar(newCalendar);

        return "redirect:/calendar";
    }
}
