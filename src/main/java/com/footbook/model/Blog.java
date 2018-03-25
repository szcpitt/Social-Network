package com.footbook.model;

import javax.persistence.*;

@Entity
@Table(name="blog")
public class Blog {

    private Long id;
    private Long user_id;
    private String content;

    public Blog(Long id, Long user_id, String content) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
    }

    public Blog(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {return id; }

    public void setId(Long id) {this.id = id; }

    public Long getUser_id() {return user_id; }

    public void setUser_id(Long user_id) {this.user_id = user_id; }

    public String getContent() {return content; }

    public void setContent(String content) {this.content = content; }

}
