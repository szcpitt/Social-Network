package com.footbook.model;

import javax.persistence.*;

@Entity
@Table(name="favorite")
public class Favorite {

    private Long id;
    private Long blogId;
    private Long ownerId;

    public Favorite(){}

    public Favorite(Long blogId, Long ownerId) {
        this.blogId = blogId;
        this.ownerId = ownerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {return id; }

    public void setId(Long id) {this.id = id; }

    public Long getBlogId() {return blogId; }

    public void setBlogId(Long blogId) {this.blogId = blogId; }

    public Long getOwnerId() {return ownerId; }

    public void setOwnerId(Long ownerId) {this.ownerId = ownerId; }
}
