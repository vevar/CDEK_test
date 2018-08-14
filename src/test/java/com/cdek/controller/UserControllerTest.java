package com.cdek.controller;


import com.cdek.domain.User;
import com.cdek.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    // TODO Why userService not work here?
    @MockBean
    private UserService userService;

    @Test
    public void contextLoad() {
        assertThat(userService).isNotNull();
    }

    @Test
    public void loadPage() throws Exception {
        mvc.perform(get("/user/new_user")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    public void postNewUser_whenUserNotExist() throws Exception {

        User ivan = new User("Ivan");

        mvc.perform(post("/user/new_user")
                .param("name", ivan.getName()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message",
                        String.format("User with name: <b> %s </b> added.", ivan.getName())));

    }

    @Test
    public void postNewUser_whenUserExist() throws Exception {

        User nikola = new User("Nikola");

        userService.addUser(nikola);

        mvc.perform(post("/user/new_user")
                .param("name", nikola.getName()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "User exists!"));

    }


    @Test
    public void getAllUsers() throws Exception {

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            User user = new User("Username#" + i);
            userService.addUser(user);
            users.add(user);
        }
        // TODO ??? and Objects
        mvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("username", ""))
                .andExpect(model().attribute("users", users));
    }

    @Test
    public void getUserByName() throws Exception {

        User nikola = new User("Nikola");
        List<User> users = Collections.singletonList(nikola);

        userService.addUser(nikola);

        mvc.perform(get("/user").param("username",nikola.getName()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("username", nikola.getName()))
                .andExpect(model().attribute("users", users));
    }


}
