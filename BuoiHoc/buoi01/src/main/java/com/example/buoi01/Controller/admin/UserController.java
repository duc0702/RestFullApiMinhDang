package com.example.buoi01.Controller.admin;

import com.example.buoi01.domain.Dto.UserDto;
import com.example.buoi01.domain.User;
import com.example.buoi01.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getListUser() {
        List<UserDto> listUser = userService.getAllUser(UserDto.class);
        return ResponseEntity.ok().body(listUser);
    }
    @GetMapping("{id}")
   public ResponseEntity<UserDto> getOneUser(@PathVariable Long id){
        UserDto user = userService.getUserById(id, UserDto.class)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User saveuser = userService.saveUser(user);
        return ResponseEntity.created(null).body(saveuser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long  id) {
        User updateUser = userService.updateUser(user, id);
        return ResponseEntity.ok().body(updateUser);


    }
}
