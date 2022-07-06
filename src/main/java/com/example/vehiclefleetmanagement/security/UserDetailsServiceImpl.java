package com.example.vehiclefleetmanagement.security;

import com.example.vehiclefleetmanagement.model.User;
import com.example.vehiclefleetmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserNameEquals(userName);
        User user = userRepository.findByUserNameEquals(userName).orElseThrow(
                () -> new UsernameNotFoundException("Not exist"));
        return new UserDetailsImplementation(userName, user.getPassword(), user.getUserRole());
    }
}
