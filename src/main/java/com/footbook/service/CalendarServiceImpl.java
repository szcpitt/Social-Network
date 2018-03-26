package com.footbook.service;

import com.footbook.model.Calendar;
import com.footbook.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public List<Calendar> findByUserId(Long userid){
        return calendarRepository.findByUserId(userid);
    }


    @Override
    public void save(Calendar calendar){
        calendarRepository.save(calendar);
    }
}
