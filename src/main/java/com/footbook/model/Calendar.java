package com.footbook.model;

import javax.persistence.*;

@Entity
@Table(name="calendar")
public class Calendar {

    private Long id;
    private Long userId;
    private String title;
    private String start;
    private String end;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {return id; }

    public void setId(Long id) {this.id = id; }

    public Long getUserId() {return userId; }

    public void setUserId(Long userId) {this.userId = userId; }

    public String getTitle() {return title; }

    public void setTitle(String title) {this.title = title; }

    public String getStart() {return start; }

    public void setStart(String start) {this.start = start; }

    public String getEnd() {return end; }

    public void setEnd(String end) {this.end = end; }

    public Calendar(){}

    public Calendar(Long userId,String title, String start, String end) {
        this.userId=userId;
        this.title = title;
        this.start = start;
        this.end = end;
    }
}
