package com.example.buoi01.controller.admin;

import com.example.buoi01.domain.dto.UserDto;
import com.example.buoi01.domain.User;
import com.example.buoi01.service.UserService;
import com.example.buoi01.service.utils.error.InvalidEmailException;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<Set<UserDto>> getListUser() {
        Set<UserDto> listUser = userService.getAllUser(UserDto.class);
        return ResponseEntity.ok().body(listUser);
    }
    @GetMapping("{id}")
   public ResponseEntity<UserDto> getOneUser(@PathVariable Long id){
        UserDto user = userService.getUserById(id, UserDto.class)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user) throws InvalidEmailException {
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
    @GetMapping("/")
    public ResponseEntity<UserDto> getUserByEmail( @RequestParam("email") String email) {

        UserDto userDto = (UserDto) userService.getUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
               
        return ResponseEntity.ok().body(userDto);
    }
    @GetMapping("/m")
    public  ResponseEntity<List<UserDto>> getAllPage(@RequestParam(value = "page") Optional<String> page,
                                                    @RequestParam(value = "size") Optional<String> size) {
                                                        int pageInteger= page.isPresent()?Integer.parseInt(page.get()) :0;
                                                        int sizeInteger= size.isPresent()?Integer.parseInt(size.get()) :10;
                                                        Pageable pageable = PageRequest.of(pageInteger, sizeInteger);
                                                        List<UserDto> listUser = userService.findAllByWithPageable(pageable).getContent();
                                                        return ResponseEntity.ok().body(listUser);
    }
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByName(@RequestParam("name") String name) {
        List<User> listUser = userService.searchByName(name);
        return ResponseEntity.ok().body(listUser);
    }
}
