package com.f5.commit_or_die.model;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    
    private String role;
    
   
}