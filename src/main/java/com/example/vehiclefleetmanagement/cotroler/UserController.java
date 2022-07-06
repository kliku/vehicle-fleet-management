package com.example.vehiclefleetmanagement.cotroler;


import com.example.vehiclefleetmanagement.domain.LoginForm;
import com.example.vehiclefleetmanagement.domain.UserAddForm;
import com.example.vehiclefleetmanagement.domain.UserDto;
import com.example.vehiclefleetmanagement.exceptions.ApplicationLogicExceptions;
import com.example.vehiclefleetmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserAddForm userAddForm) {
        try {
            userService.addUser(userAddForm);
            return ResponseEntity.ok("Complete user add");
        } catch (ApplicationLogicExceptions e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Delete user with id:" + id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Not find user id: " + id);
        }
    }

    @GetMapping(value = "/userList")
    public List<UserDto> getUserList() {
        return userService.getUserList();
    }

    @PostMapping(value = "/assignCompany/{idUser}/{idCompany}")
    public void assignUserToCompany(@PathVariable Long idUser, @PathVariable Long idCompany) {
        userService.assignUserToCompany(idUser, idCompany);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginForm loginForm) {
        return userService.login(loginForm);
    }
}
