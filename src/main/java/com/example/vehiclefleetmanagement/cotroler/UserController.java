package com.example.vehiclefleetmanagement.cotroler;


import com.example.vehiclefleetmanagement.domain.UserAddForm;
import com.example.vehiclefleetmanagement.domain.UserDto;
import com.example.vehiclefleetmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public void addUser(@RequestBody UserAddForm userAddForm) {
        userService.addUser(userAddForm);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/userList")
    public List<UserDto> getUserList() {
        return userService.getUserList();
    }

    @PostMapping(value = "/assignCompany/{idUser}/{idCompany}")
    public void assignUserToCompany(@PathVariable Long idUser, @PathVariable Long idCompany) {
        userService.assignUserToCompany(idUser, idCompany);
    }
}
