package com.titrate.essentialism.services;

import com.titrate.essentialism.models.PersonalValue;

import java.util.List;

public interface PersonalValueService
{
    List<PersonalValue> findAll();

    PersonalValue findPersonalValueById(long id);

    List<PersonalValue> findByUserName(String username);

    void delete(long id);

    PersonalValue save(PersonalValue quote);
}
