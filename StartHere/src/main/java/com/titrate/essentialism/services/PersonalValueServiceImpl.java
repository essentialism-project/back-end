package com.titrate.essentialism.services;

import com.titrate.essentialism.exceptions.ResourceNotFoundException;
import com.titrate.essentialism.models.PersonalValue;
import com.titrate.essentialism.repository.PersonalValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "personalValueService")
public class PersonalValueServiceImpl implements PersonalValueService
{
    @Autowired
    private PersonalValueRepository personalValueService;

    @Override
    public List<PersonalValue> findAll()
    {
        List<PersonalValue> list = new ArrayList<>();
        personalValueService.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public PersonalValue findPersonalValueById(long id)
    {
        return personalValueService.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (personalValueService.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (personalValueService.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                personalValueService.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(id + " " + authentication.getName());
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public PersonalValue save(PersonalValue value)
    {
        return personalValueService.save(value);
    }

    @Override
    public List<PersonalValue> findByUserName(String username)
    {
        List<PersonalValue> list = new ArrayList<>();
        personalValueService.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Transactional
    @Override
    public PersonalValue updateById(PersonalValue value, Long id) {
        PersonalValue currentValue = personalValueService.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if(value.getPersonalvalue() != null){
        currentValue.setPersonalvalue(value.getPersonalvalue());
        }
        if(value.getDescription() != null){
            currentValue.setDescription(value.getDescription());
        }
        if(value.getProjects().size() != 0){
            currentValue.getProjects().addAll(value.getProjects());
        }
        return value;
    }
}
