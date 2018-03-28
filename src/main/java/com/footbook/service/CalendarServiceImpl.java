package com.footbook.service;

import com.footbook.dao.CalendarDao;
import com.footbook.model.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 */
@Service
@Transactional
public class CalendarServiceImpl implements CalendarService{

    @Autowired
    private CalendarDao calendarDao;

    @Override
    public List<Calendar> getAllCalendar(int userId) {
        return calendarDao.getAllCalendar(userId);
    }

    @Override
    public void addCalendar(Calendar calendar) {
        calendarDao.addCalendar(calendar);
    }

    @Override
    public Calendar getUpcomingEvent(int user_id) {
        return calendarDao.upcoming(user_id);
    }
}
