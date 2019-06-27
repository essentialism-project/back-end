package com.titrate.essentialism.controllers;

import com.titrate.essentialism.models.PersonalValue;
import com.titrate.essentialism.models.Role;
import com.titrate.essentialism.models.User;
import com.titrate.essentialism.models.UserRoles;
import com.titrate.essentialism.services.RoleService;
import com.titrate.essentialism.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {
//

    @MockBean
    UserService userService;


    @MockBean
    RoleService roleService;

    @Autowired
    MockMvc mockMvc;

    List<User> userList;
    @Before
    public void setUp() throws Exception {
        userList = new ArrayList<>();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getPersonalvalues().add(new PersonalValue("Relationships With Friends and Family", u1));
        u1.getPersonalvalues().add(new PersonalValue("Independence", u1));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getPersonalvalues().add(new PersonalValue("Live long and prosper", u3));
        u3.getPersonalvalues().add(new PersonalValue("Moral Principles", u3));
        u3.getPersonalvalues().add(new PersonalValue("Creativity", u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() {
        String apiUrl = "/users/users";

        Mockito.when(userService.findAll()).thenReturn(userList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(restaurantList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getUser() {
    }

    @Test
    public void getCurrentUser() {
    }

    @Test
    public void addNewUser() {

    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserById() {
    }
}