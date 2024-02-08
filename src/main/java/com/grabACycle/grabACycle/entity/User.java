package com.grabACycle.grabACycle.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
    private Date dob;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "roleId")
    )
    private Collection<Role> roles;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getDob() {
        return dob;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String password, Date dob, Collection<Role> roles) {

        this.username = username;
        this.password = password;
        this.dob = dob;
        this.roles = roles;
    }

    public User() {
        super();
    }
}
