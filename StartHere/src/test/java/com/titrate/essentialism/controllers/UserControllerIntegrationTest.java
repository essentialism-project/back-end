package com.titrate.essentialism.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titrate.essentialism.models.PersonalValue;
import com.titrate.essentialism.models.User;
import com.titrate.essentialism.models.UserRoles;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
    @Test
    public void whenMeasuredReponseTime()
    {
        given().when().get("/users/users").then().time(lessThan(5000L));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() throws Exception{
        ArrayList<UserRoles> thisPay = new ArrayList<>();
        User r3 = new User("notadmin", "password", thisPay);


        ObjectMapper mapper = new ObjectMapper();
        String stringR3 = mapper.writeValueAsString(r3);

        given().contentType("application/json").body(stringR3).when().post("/users/users").then().statusCode(201);
    }

    @Test
    public void getUser() {
    }

    @Test
    public void getCurrentUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserById() {
    }
}