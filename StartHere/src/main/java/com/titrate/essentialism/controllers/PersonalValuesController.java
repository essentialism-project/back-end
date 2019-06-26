package com.titrate.essentialism.controllers;


import com.titrate.essentialism.models.PersonalValue;
import com.titrate.essentialism.models.User;
import com.titrate.essentialism.services.PersonalValueService;
import com.titrate.essentialism.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/personalvalues")
public class PersonalValuesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    PersonalValueService personalValueService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "Returns a list of all personal values", response = PersonalValue.class)
    @GetMapping(value = "/personalvalues",
                produces = {"application/json"})
    public ResponseEntity<?> listAllPersonalValues(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<PersonalValue> allPersonalValues = personalValueService.findAll();
        return new ResponseEntity<>(allPersonalValues, HttpStatus.OK);
    }


    @GetMapping(value = "/personalValue/{personalValueId}",
                produces = {"application/json"})
    public ResponseEntity<?> getPersonalValue(HttpServletRequest request,
                                      @PathVariable
                                              Long personalValueId)
    {
        logger.trace(request.getRequestURI() + " accessed");

        PersonalValue q = personalValueService.findPersonalValueById(personalValueId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findPersonalValueByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<PersonalValue> thePersonalValues = personalValueService.findByUserName(userName);
        return new ResponseEntity<>(thePersonalValues, HttpStatus.OK);
    }
//
//
//    @PutMapping(value = "/users/{userid}/values/{valueIndex}/")
//    public ResponseEntity<?> updateUsersPersonalvalue(@Valid @RequestBody PersonalValue jsonPersonalValue,
//    @PathVariable long userid, @PathVariable int valueIndex){
//    User currentUser = userService.findUserById(userid);
//        for (int i = 0; i < currentUser.getPersonalvalues().size(); i++) {
//                if(i == valueIndex){
//                    currentUser.getPersonalvalues().set(valueIndex,jsonPersonalValue);
//                }
//        }
//    return new ResponseEntity<>(HttpStatus.OK);
//        }

//@PutMapping("/personalValue/{id}")
//public ResponseEntity<?> updatePersonalValue(HttpServletRequest request,
//                                    @RequestBody
//                                            User updateValue,
//                                    @PathVariable
//                                            long id)
//{
//    logger.trace(request.getRequestURI() + " accessed");
//
//    personalValueService.update(updateValue, id);
//    return new ResponseEntity<>(HttpStatus.OK);
//}

    @DeleteMapping("/personalValue/{id}")
    public ResponseEntity<?> deletePersonalValueById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        personalValueService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
