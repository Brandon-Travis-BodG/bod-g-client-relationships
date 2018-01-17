package com.brandongossen.bodg.clientmanager.services;

import com.brandongossen.bodg.clientmanager.models.User;
import com.brandongossen.bodg.clientmanager.models.UserWithRoles;
import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository usersDao;

    @Autowired
    public UserDetailsLoader(UsersRepository usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user, Collections.emptyList());
    }
}