package com.doppalapudi.platform.userreg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doppalapudi.platform.userreg.model.User;
import com.doppalapudi.platform.userreg.model.UserPricipal;
import com.doppalapudi.platform.userreg.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = usersRepository.findByUsernameLike(userName);
        LOGGER.debug("User : {}", user);
        if(user == null) {
            throw new UsernameNotFoundException("User Not Found.");
        }
        return new UserPricipal(user);
    }
}
