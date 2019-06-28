package com.titrate.essentialism.services;

import com.titrate.essentialism.StartHereApplication;
import com.titrate.essentialism.models.PersonalValue;
import com.titrate.essentialism.models.User;
import com.titrate.essentialism.models.UserRoles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;
    private int userSize = 5;
    @Test
    public void loadUserByUsername() {
    }

    @Test
    public void findUserById() {
        assertEquals("admin", userService.findUserById(4).getUsername());
    }

    @Test
    public void findAll() {
        assertEquals(userSize, userService.findAll().size());
    }

    @Test
    public void delete() {
        userService.delete(8);
        assertEquals(userSize-1, userService.findAll().size());
    }

    @Test
    public void save() {
        ArrayList<UserRoles> thisPay = new ArrayList<>();

        User r3 = new User("ruben", "password", thisPay);
        r3.getPersonalvalues().add(new PersonalValue("Pizza",r3));

        User addUser = userService.save(r3);

        assertNotNull(addUser);

        User foundUser = userService.findUserById(addUser.getUserid());
        assertEquals(addUser.getUsername(), foundUser.getUsername());
    }

    @Test
    public void findUserByName() {
    }

    @Test
    public void update() {
    }
}