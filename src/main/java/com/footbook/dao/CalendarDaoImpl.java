package com.footbook.dao;

import com.footbook.model.Calendar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Jacob Huang
 * Project Name: mvc
 * Date: 2018/3/3
 * Time: 08:17
 */

@Repository
public class CalendarDaoImpl implements CalendarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Calendar> getAllCalendar(int userId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Calendar where userId=:userId";

        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);

        return query.list();
    }

    @Override
    public void addCalendar(Calendar calendar) {
        Session session = sessionFactory.getCurrentSession();
        session.save(calendar);
    }

    @Override
    public Calendar upcoming(int user_id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Calendar where userId=:user_id ORDER BY start";
        Query query = session.createQuery(hql);
        query.setParameter("user_id", user_id);

        query.setMaxResults(1);

        Calendar calendar = (Calendar) query.uniqueResult();
        return calendar;

    }
}
