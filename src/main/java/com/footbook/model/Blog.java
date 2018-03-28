package com.footbook.model;

import javax.persistence.*;

@Entity
@Table(name="blog")
public class Blog {

    private Long id;
    private Long userId;
    private String content;

    public Blog(Long id, Long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public Blog(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {return id; }

    public void setId(Long id) {this.id = id; }

    public Long getUserId() {return userId; }

    public void setUserId(Long userId) {this.userId = userId; }

    public String getContent() {return content; }

    public void setContent(String content) {this.content = content; }

}
