package com.titrate.essentialism.services;

import com.titrate.essentialism.exceptions.ResourceNotFoundException;
import com.titrate.essentialism.models.PersonalValue;
import com.titrate.essentialism.repository.PersonalValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "personalValueService")
public class PersonalValueServiceImpl implements PersonalValueService
{
    @Autowired
    private PersonalValueRepository quoterepos;

    @Override
    public List<PersonalValue> findAll()
    {
        List<PersonalValue> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public PersonalValue findPersonalValueById(long id)
    {
        return quoterepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (quoterepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (quoterepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                quoterepos.deleteById(id);
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
    public PersonalValue save(PersonalValue quote)
    {
        return quoterepos.save(quote);
    }

    @Override
    public List<PersonalValue> findByUserName(String username)
    {
        List<PersonalValue> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
