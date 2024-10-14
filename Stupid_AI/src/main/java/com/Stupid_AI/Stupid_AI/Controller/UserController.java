package com.Stupid_AI.Stupid_AI.Controller;

import com.Stupid_AI.Stupid_AI.Entity.UserEntity;
import com.Stupid_AI.Stupid_AI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Thêm mới người dùng
    @PostMapping("/add")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Lấy tất cả người dùng
    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Lấy người dùng theo ID
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable int id) {
        UserEntity user = userService.getUserById(id);
        if (user != null) {
            return user;
        } else {
            return null ;
        }
    }

    // Sửa thông tin người dùng
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id, @RequestBody UserEntity updatedUser) {
        UserEntity user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa người dùng
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
