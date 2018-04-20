package com.anderson.adsc137.repository;

import com.anderson.adsc137.model.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findById(long id);
}
