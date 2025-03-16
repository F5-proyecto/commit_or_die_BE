package com.f5.commit_or_die.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.services.UserService;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testGetUser_UserExists() throws Exception {
      
        User user = new User();
        user.setId(1L);
        user.setName("Yefer Gaviria"); 

        when(userService.findById(1L)).thenReturn(user);

    
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Yefer Gaviria"));
    }

    @Test
    public void testGetUser_UserNotFound() throws Exception {

        when(userService.findById(1L)).thenReturn(null);

      
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUser_UserExists() throws Exception {
  
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Old Name");

        when(userService.findById(1L)).thenReturn(existingUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Name\"}"))
                .andExpect(status().isOk());

      
        verify(userService).update(argThat(user -> 
            user.getId().equals(1L) && user.getName().equals("New Name")
        ));
    }

    @Test
    public void testUpdateUser_UserNotFound() throws Exception {
       
        when(userService.findById(1L)).thenReturn(null);

    
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Name\"}"))
                .andExpect(status().isNotFound());

      
        verify(userService, never()).update(any(User.class));
    }
}