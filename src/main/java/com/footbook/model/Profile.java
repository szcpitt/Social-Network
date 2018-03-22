package com.footbook.model;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    private Long id;
    private String userId;
    private String username;
    private String gender;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    @OneToOne
    @JoinTable(name = "user_profile", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
