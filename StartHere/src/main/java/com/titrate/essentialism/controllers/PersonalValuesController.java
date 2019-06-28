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
