package com.f5.commit_or_die.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.f5.commit_or_die.model.User;
import com.f5.commit_or_die.repository.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_UserExists() {

        User user = new User();
        user.setId(1L);
        user.setName("Yefer Gaviria");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));


        User foundUser = userService.findById(1L);


        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        assertEquals("Yefer Gaviria", foundUser.getName());
    }

    @Test
    public void testFindById_UserNotFound() {
  
        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        User foundUser = userService.findById(1L);


        assertNull(foundUser);
    }

    @Test
    public void testUpdate_UserSaved() {

        User user = new User();
        user.setId(1L);
        user.setName("Updated Name");

        when(userRepository.save(user)).thenReturn(user);


        userService.update(user);


        verify(userRepository).save(user);
    }
}
