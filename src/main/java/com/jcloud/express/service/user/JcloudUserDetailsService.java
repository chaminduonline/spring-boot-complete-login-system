package com.jcloud.express.service.user;

import com.jcloud.express.model.Role;
import com.jcloud.express.model.User;
import com.jcloud.express.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.LoginAttemptService;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class JcloudUserDetailsService implements UserDetailsService {

    private LoginAttemptService loginAttemptService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new BadCredentialsException("Invalid Username or Password");
        }
        if (user.isEnabled()){
            org.springframework.security.core.userdetails.User loginUser = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    buildAutorities(user.getRoles()));
            return loginUser;
        }
        throw new InternalAuthenticationServiceException("Access to your account is temporarily disabled.\n Please contact your administrator");
    }

    private List<GrantedAuthority> buildAutorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

}
