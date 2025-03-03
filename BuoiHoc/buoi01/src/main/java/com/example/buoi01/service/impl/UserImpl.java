package com.example.buoi01.service.impl;

import com.example.buoi01.repository.UserRepository;
import com.example.buoi01.domain.User;
import com.example.buoi01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserImpl implements UserService {
    @Autowired
 private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;


//    @Override
//    public List<User> getListUser() {
//        return  userRepository.findAll();
//    }


    @Override
    public <T> List<T> getAllUser(Class<T> type) {
        return userRepository.findAllBy(type);
    }

    @Override
    public User saveUser(User user) {
      if (user.getPassword()!=null&& !user.getPassword().isEmpty()){
          String hashPass= passwordEncoder.encode(user.getPassword());
          user.setPassword(hashPass);
      }

          User current = this.userRepository.save(user);
        return current;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public <T> Optional<T> getUserById(Long id, Class<T> type) {
        return userRepository.findById(id,type);

    }

    @Override
    public <T> Optional <T> getUserByEmail(String email, Class<T> type) {
    Optional<T> user = userRepository.findByEmail(email,type);
        return user;
    }


    @Override
    public User updateUser(User user, long id) {
        Optional<User> detailUser = userRepository.findById(id);
        if (detailUser.isPresent()){
            if (user.getPassword()!=null&& !user.getPassword().isEmpty()){
                String hashPass= passwordEncoder.encode(user.getPassword());
                user.setPassword(hashPass);
            }

            return  userRepository.save(user);
        }
        else {
            throw new RuntimeException("Khong tim thay ");
        }
    }
}
