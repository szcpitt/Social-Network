package com.footbook.model;


import javax.persistence.*;

@Entity
@Table(name="relationship")
public class Relationship {


    private Long id;
    private Long user_id;
    private Long friend_id;

    private Profile owner;
    private Profile friend;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    public Profile getOwner() { return owner; }

    public void setOwner(Profile owner) { this.owner = owner; }

    @ManyToOne
    @JoinColumn(name = "friend_id",insertable = false, updatable = false)
    public Profile getFriend() { return friend; }

    public void setFriend(Profile friend) { this.friend = friend; }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {return id; }

    public void setId(Long id) {this.id = id; }


    public Long getUser_id() {return user_id; }

    public void setUser_id(Long user_id) {this.user_id = user_id; }


    public Long getFriend_id() {return friend_id; }

    public void setFriend_id(Long friend_id) {this.friend_id = friend_id; }

    public Relationship(){}

    public Relationship(Long user_id,Long friend_id){
        this.user_id=user_id;
        this.friend_id=friend_id;
    }

}
