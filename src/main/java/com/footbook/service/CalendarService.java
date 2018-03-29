package com.footbook.service;

import com.footbook.model.Calendar;

import java.util.List;

public interface CalendarService {

    public List<Calendar> getAllCalendar(int userId);

    public void addCalendar(Calendar calendar);

    public Calendar getUpcomingEvent(int user_id);

}
