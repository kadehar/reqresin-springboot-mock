package com.github.kadehar.reqresinspringbootmock.controller;

import com.github.kadehar.reqresinspringbootmock.entity.User;
import com.github.kadehar.reqresinspringbootmock.exceptions.UserNotFoundException;
import com.github.kadehar.reqresinspringbootmock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User newUser) {
        User user = new User(newUser.getName(), newUser.getJob());
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@Valid @RequestBody User newUser, @PathVariable Long userId) {
        User user = userRepository.findById(userId).map(usr -> {
            usr.setName(newUser.getName());
            usr.setJob(newUser.getJob());
            usr.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(usr);
        }).orElseGet(() -> {
            newUser.setId(userId);
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userRepository.deleteById(userId);

        return ResponseEntity.noContent().build();
    }
}
