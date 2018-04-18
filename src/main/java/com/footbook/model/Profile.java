package com.footbook.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;

    private User user;

    private Set<Relationship> owners;

    private Set<Relationship> friends;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){this.firstName=firstName;}

    public String getLastName(){return lastName;}

    public void setLastName(String lastName){this.lastName=lastName;}

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getImage(){ return image;}

    public void setImage(String image){ this.image=image;}

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId",insertable = false, updatable = false)
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @OneToMany( mappedBy="owner", cascade=CascadeType.MERGE, orphanRemoval = true)
    public Set<Relationship> getOwners() {return owners; }

    public void setOwners(Set<Relationship> owners) {this.owners = owners; }

    @OneToMany( mappedBy="friend", cascade=CascadeType.MERGE, orphanRemoval = true)
    public Set<Relationship> getFriends() {return friends; }

    public void setFriends(Set<Relationship> friends) {this.friends = friends; }

    public Profile(){}

    public Profile(String firstName,String lastName,String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
    }


}
