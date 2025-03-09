package com.f5.commit_or_die.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    private Date fixedDate;

    @BeforeEach
    public void setUp() {
        fixedDate = new Date(1633024800000L); 
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setRegistrationDate(fixedDate);
        user.setRole("USER");
    }

    @Test
    public void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetRegistrationDate() {
        // Comparamos los tiempos para evitar problemas con millisegundos
        assertEquals(fixedDate.getTime(), user.getRegistrationDate().getTime());
    }

    @Test
    public void testGetRole() {
        assertEquals("USER", user.getRole());
    }

    @Test
    public void testSetId() {
        user.setId(2L);
        assertEquals(2L, user.getId());
    }

    @Test
    public void testSetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testSetRegistrationDate() {
        Date newDate = new Date(1633111200000L);
        user.setRegistrationDate(newDate);
        assertEquals(newDate.getTime(), user.getRegistrationDate().getTime());
    }

    @Test
    public void testSetRole() {
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }
}   
