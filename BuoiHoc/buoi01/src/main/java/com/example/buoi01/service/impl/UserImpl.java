package com.example.buoi01.service.impl;

import com.example.buoi01.repository.UserRepository;
import com.example.buoi01.domain.User;
import com.example.buoi01.domain.dto.UserDto;
import com.example.buoi01.domain.specs.Userspecs;
import com.example.buoi01.service.UserService;
import com.example.buoi01.service.utils.error.InvalidEmailException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public <T> Set<T> getAllUser(Class<T> type) {
        return userRepository.findAllBy(type);
    }

    @Override
    public User saveUser(User user) throws InvalidEmailException {
        String email = user.getEmail();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new InvalidEmailException("Email đã tồn tại");
        }
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
    public <T> Optional <T> getUserByEmail(String email) {
    Optional<T> user = userRepository.findByEmail(email);
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

    @Override
    public void updateRefreshToken(String email, String refreshToken) {
        // TODO Auto-generated method stub
        Optional<User> optional = this.userRepository.findByEmail(email);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setRefreshToken(refreshToken);
            this.userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public Optional<User> getUserByRefreshTokenAndEmail(String email, String refreshToken) {
       
        return userRepository.findByEmailAndRefreshToken(email, refreshToken);
        
        
    }

    @Override
    public Page<UserDto> findAllByWithPageable(Pageable pageable) {
       return userRepository.findAllBy(pageable);
    }

    @Override
    public List<User> searchByName(String name) {
      Specification<User> spec = Userspecs.nameLike(name);
      return userRepository.findAll(spec);
       
    }
}
