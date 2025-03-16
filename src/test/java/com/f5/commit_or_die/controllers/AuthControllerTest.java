package com.f5.commit_or_die.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.f5.commit_or_die.dto.AuthRequest;
import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLogin_Success() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("password");

        when(authService.loginAndGetUser("test@example.com", "password")).thenReturn(user);


        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.userId").value("1"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
   
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("wrong@example.com");
        authRequest.setPassword("wrong");

        when(authService.loginAndGetUser("wrong@example.com", "wrong")).thenReturn(null);


        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }

    @Test
    public void testRegister_Success() throws Exception {

        User newUser = new User();
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("password");

        User registeredUser = new User();
        registeredUser.setId(2L);
        registeredUser.setEmail("newuser@example.com");

        when(authService.register(any(User.class))).thenReturn(registeredUser);


        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }
}