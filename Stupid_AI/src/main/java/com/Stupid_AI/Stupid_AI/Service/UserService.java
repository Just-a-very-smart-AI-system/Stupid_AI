package com.Stupid_AI.Stupid_AI.Service;

import com.Stupid_AI.Stupid_AI.Entity.UserEntity;
import com.Stupid_AI.Stupid_AI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Thêm mới người dùng
    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    // Lấy danh sách tất cả người dùng
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Tìm người dùng theo ID
    public UserEntity getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong co Id"));
    }

    // Sửa thông tin người dùng
    public UserEntity updateUser(int id, UserEntity updatedUser) {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
            user.setUserName(updatedUser.getUserName());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        } else {
            return null; // Hoặc xử lý lỗi nếu cần
        }
    }

    // Xóa người dùng
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
