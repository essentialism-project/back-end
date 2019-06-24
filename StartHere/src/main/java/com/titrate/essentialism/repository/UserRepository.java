package com.titrate.essentialism.repository;

import com.titrate.essentialism.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
