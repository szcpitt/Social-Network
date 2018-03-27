package com.footbook.dao;

import com.footbook.model.Calendar;

import java.util.List;

public interface CalendarDao {

    // get all based on user_id
    public List<Calendar> getAllCalendar(int user_id);

    // add a new calendar
    public void addCalendar(Calendar calendar);

}
