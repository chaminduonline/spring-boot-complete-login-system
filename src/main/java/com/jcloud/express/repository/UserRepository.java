package com.jcloud.express.repository;

import com.jcloud.express.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    public User findByUsername(String username);

}
