package com.example.vehiclefleetmanagement.service;


import com.example.vehiclefleetmanagement.domain.CompanyDto;
import com.example.vehiclefleetmanagement.domain.UserAddForm;
import com.example.vehiclefleetmanagement.domain.UserDto;
import com.example.vehiclefleetmanagement.model.Company;
import com.example.vehiclefleetmanagement.model.User;
import com.example.vehiclefleetmanagement.repository.CompanyRepository;
import com.example.vehiclefleetmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public void addUser(UserAddForm userAddForm) {
        User user = new User();
        user.setUserName(userAddForm.getUserName());
        user.setPassword(userAddForm.getPassword());
        user.setEmail(userAddForm.getEmail());
        user.setCreateDate(LocalDateTime.now());
        user.setUserRole(userAddForm.getUserRole());
        Company company = companyRepository.getById(userAddForm.getCompanyId());
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
        if (userOptional.isPresent() && companyOptional.isPresent()){
            Company company = companyOptional.get();
            User user = userOptional.get();
            user.setCompany(company);
            userRepository.save(user);
        }
    }

}
