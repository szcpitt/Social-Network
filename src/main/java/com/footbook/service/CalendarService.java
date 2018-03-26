package com.footbook.service;

import com.footbook.model.Calendar;

import java.util.List;

public interface CalendarService {

    void save(Calendar calendar);

    List<Calendar> findByUserId(Long userid);


}
