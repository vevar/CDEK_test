package com.cdek.service;


import com.cdek.domain.User;
import com.cdek.repos.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Test
    public void whenUserNotExist_thenUserAddedDB(){
        User user = new User("Alesha");
        assertTrue(userService.addUser(user));
        User foundUser = userRepository.findByName(user.getName());
        assertEquals(user.getName(),foundUser.getName());
    }

    @Test
    public void whenUserExist_thenFalse(){
        userService.addUser(new User("Alesha"));
        assertFalse(userService.addUser(new User("Alesha")));

    }
}
