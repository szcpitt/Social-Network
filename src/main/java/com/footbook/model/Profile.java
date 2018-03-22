package com.footbook.model;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    private Long id;
    private Long userid;
    private String firstName;
    private String lastName;
    private String gender;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() { return userid; }

    public void setUserId(Long userId) { this.userid = userId; }

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){this.firstName=firstName;}

    public String getLastName(){return lastName;}

    public void setLastName(String lastName){this.lastName=lastName;}

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public Profile(){}

    public Profile(String firstName,String lastName,String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
    }

}
