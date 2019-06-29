package com.titrate.essentialism.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
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



    @MockBean
    UserService userService;


    @MockBean
    RoleService roleService;

    @Autowired
    MockMvc mockMvc;

    List<User> userList;

    @Before
    public void setUp() throws Exception {
//        userList = new ArrayList<>();
////        userList.addAll(userService.findAll());
////        System.out.println(userList);
        userList = new ArrayList<>();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");



        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getPersonalvalues().add(new PersonalValue("Relationships With Friends and Family", u1));
        u1.getPersonalvalues().add(new PersonalValue("Independence", u1));


        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);


        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getPersonalvalues().add(new PersonalValue("Live long and prosper", u3));
        u3.getPersonalvalues().add(new PersonalValue("Moral Principles", u3));
        u3.getPersonalvalues().add(new PersonalValue("Creativity", u3));


        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);


        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);


        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        userList.add(u5);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() throws Exception{
        String apiUrl = "/users/users";

        Mockito.when(userService.findAll()).thenReturn(userList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        assertEquals("Rest API Returns List", er, tr);
    }


    @Test
    public void getUser() throws Exception{

        String apiUrl = "/users/user/4";
//    User currentUser = userService.findUserById(4);
        Mockito.when(userService.findUserById(4)).thenReturn(userList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(1));

        assertEquals("Rest API Returns List", er, tr);
    }

// Uses Authentication, therefore not currently testable
//    @Test
//    public void getCurrentUser() throws Exception {
////        User@72168258
//        String apiUrl = "/users/getcurrentuser";
//        Mockito.when(userService.findUserByName("barnbarn")).thenReturn(userList.get(2));
////        System.out.println(userService.findUserByName("admin").getUsername());
////        System.out.println(userList.get(0).getUsername());
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//
//        // the following actually performs a real controller call
//        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
//        String tr = r.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String er = mapper.writeValueAsString(userList.get(2));
//
//        assertEquals("Rest API Returns List", er, tr);
//    }



    @Test
    public void updateUser() throws Exception{
        String apiUrl = "/users/user/{userid}";
        ArrayList<UserRoles> thisPay = new ArrayList<>();
        User r1 = new User("ruben", "ruben",thisPay);
        r1.setUserid(10);

        Mockito.when(userService.update(r1, 10L)).thenReturn(r1);
        ObjectMapper mapper = new ObjectMapper();
        String restaurantString = mapper.writeValueAsString(r1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 10L)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(restaurantString);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserById() throws Exception{
        String apiUrl = "/users/user/{userid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "12").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}