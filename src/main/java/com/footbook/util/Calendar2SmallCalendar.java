package com.footbook.util;
/*
* Author: Jacob Huang
* Project Name: account
* Date: 2018/3/26
* Time: 22:28
*/

/*

*/

import com.footbook.model.Calendar;
import com.footbook.model.SmallCalendar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Calendar2SmallCalendar {
    public List<SmallCalendar> ConvertAll(List<Calendar> AllCalendar){

        List<SmallCalendar> res = new ArrayList<>();

        for (Calendar cal : AllCalendar){
            String title = cal.getTitle();
            String start = cal.getStart();
            String end = cal.getEnd();

            SmallCalendar temp = new SmallCalendar();
            temp.setTitle(title);
            temp.setStart(start);
            temp.setEnd(end);

            res.add(temp);
        }

        return res;
    }


    public SmallCalendar convertOne(Calendar OneCalendar){
        String title = OneCalendar.getTitle();
        String start = OneCalendar.getStart();
        String end = OneCalendar.getEnd();

        SmallCalendar temp = new SmallCalendar();
        temp.setTitle(title);
        temp.setStart(start);
        temp.setEnd(end);
        return temp;
    }
}
