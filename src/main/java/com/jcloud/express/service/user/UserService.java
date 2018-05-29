package com.jcloud.express.service.user;

import com.jcloud.express.model.User;

public interface UserService {

    /**
     * check existing root user
     * @return
     */
    public boolean hasAdmin();

    /**
     * create admin user
     * @param user
     */
    public void createRootUser(User user);


    public User findByUserUsername(String username,String password);
}
