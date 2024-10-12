package com.mid.ruayruayruay.service;

import com.mid.ruayruayruay.model.User;
import com.mid.ruayruayruay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // BCryptPasswordEncoder สำหรับเข้ารหัสรหัสผ่าน
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ฟังก์ชันตรวจสอบการเข้าสู่ระบบ
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("ไม่พบผู้ใช้: " + username);
            return false;
        }
        boolean isMatch = passwordEncoder.matches(password, user.getPassword());
        System.out.println("รหัสผ่านตรงกันหรือไม่: " + isMatch);
        return isMatch;
    }

    // ฟังก์ชันสร้างผู้ใช้ใหม่ (ถ้าจำเป็น)
    public void registerUser(User user) {
        // เข้ารหัสรหัสผ่านก่อนบันทึกลงฐานข้อมูล
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // ฟังก์ชันค้นหาผู้ใช้โดย ID
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // ฟังก์ชันค้นหาผู้ใช้โดยชื่อผู้ใช้
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
