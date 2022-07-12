package com.example.vehiclefleetmanagement.service;


import com.example.vehiclefleetmanagement.domain.LoginForm;
import com.example.vehiclefleetmanagement.domain.UserAddForm;
import com.example.vehiclefleetmanagement.domain.UserDto;
import com.example.vehiclefleetmanagement.exceptions.ApplicationLogicExceptions;
import com.example.vehiclefleetmanagement.model.Company;
import com.example.vehiclefleetmanagement.model.User;
import com.example.vehiclefleetmanagement.repository.CompanyRepository;
import com.example.vehiclefleetmanagement.repository.UserRepository;
import com.example.vehiclefleetmanagement.security.JwtUtils;
import com.example.vehiclefleetmanagement.security.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public void addUser(UserAddForm userAddForm) throws ApplicationLogicExceptions {
        User user = new User();
        user.setUserName(userAddForm.getUserName());
        user.setPassword(passwordEncoder.encode(userAddForm.getPassword()));
        user.setEmail(userAddForm.getEmail());
        user.setCreateDate(LocalDateTime.now());
        user.setUserRole(userAddForm.getUserRole());
        Company company = companyRepository.findById(userAddForm.getCompanyId()).orElseThrow(
                () -> new ApplicationLogicExceptions("Not find company"));
        user.setCompany(company);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getUserList() {
        List<User> userList = userRepository.findAll();
        List<UserDto> resultList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setUserRole(user.getUserRole());
            userDto.setCompanyId(user.getCompany().getId());
            resultList.add(userDto);
        }
        return resultList;
    }

    public void assignUserToCompany(Long idUser, Long idCompany) {
        Optional<User> userOptional = userRepository.findById(idUser);
        Optional<Company> companyOptional = companyRepository.findById(idCompany);
        if (userOptional.isPresent() && companyOptional.isPresent()) {
            Company company = companyOptional.get();
            User user = userOptional.get();
            user.setCompany(company);
            userRepository.save(user);
        }
    }

    public String login(LoginForm loginForm) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateToken(authentication);
    }

    public User getCurrentLoggerUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplementation userDetailsImplementation = (UserDetailsImplementation) authentication.getPrincipal();
        return userRepository.findByUserNameEquals(userDetailsImplementation.getUsername()).orElseThrow();
    }

}
