package com.example.vtube.config;

import com.example.vtube.dao.entities.Role;
import com.example.vtube.dao.repository.UserRepository;
import com.example.vtube.dao.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String userName,password;
        String role;
        List<User> user=userRepository.findByUsername(username);
        if(user.size()==0){
            throw new UsernameNotFoundException("user details not found for the user"+username);
        }else{
            userName=user.get(0).getUsername();
            password=user.get(0).getPassword();
            role=user.get(0).getRole().name();
        }
        return org.springframework.security.core.userdetails.User.builder()
                    .username(userName)
                    .password(password)
                    .roles(role)
                    .build();
    }

}
