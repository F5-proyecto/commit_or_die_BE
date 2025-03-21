package com.f5.commit_or_die.model;

import jakarta.persistence.*;
import java.util.Date;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size (max = 100, min = 3)
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    private String role;

    public User() {
    }

    public User(String name, String email, String password, Date registrationDate, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public User(Long id, String name, String email, String password, Date registrationDate, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        if (this.registrationDate == null) {
            this.registrationDate = new Date();
        }
    }
}