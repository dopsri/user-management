package com.doppalapudi.platform.userreg.repository;

import com.doppalapudi.platform.userreg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {
    User findByUsernameLike(String name);       
}
