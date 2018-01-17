package com.brandongossen.bodg.clientmanager.repositories;

import com.brandongossen.bodg.clientmanager.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository{
    User findByUsername(String username);
}
