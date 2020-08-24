package com.diary.demo.service;

import com.diary.demo.entity.User;
import com.diary.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userRepository.findByUsername(s);
        User userFindByUsername = userRepository.findByUsername(s);
        User userFindByGoogleUsername = userRepository.findByGoogleUsername(s);
        User userFindByGoogleName = userRepository.findByGoogleName(s);

        if(userFindByUsername != null)
        {
            return userFindByUsername;
        }

        if(userFindByGoogleUsername != null)
        {
            return userFindByGoogleUsername;
        }

        if(userFindByGoogleName != null)
        {
            return userFindByGoogleName;
        }

        return null;
    }
}
