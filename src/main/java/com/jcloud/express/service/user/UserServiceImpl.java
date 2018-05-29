package com.jcloud.express.service.user;

import com.jcloud.express.model.Role;
import com.jcloud.express.model.User;
import com.jcloud.express.repository.RoleRepository;
import com.jcloud.express.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean hasAdmin() {
        return userRepository.findByUsername("root") != null;
    }

    @Override
    public User findByUserUsername(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (passwordEncoder.matches(password,user.getPassword())) {
            System.out.println("user"+user);
            return user;
        }
        return null;
    }

    @Override
    public void createRootUser(User user) {
        try {
            Role role = roleRepository.save(new Role("ROLE_ADMIN"));
            user.setRoles(Arrays.asList(role));
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
