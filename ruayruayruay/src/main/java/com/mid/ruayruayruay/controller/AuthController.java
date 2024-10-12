package com.mid.ruayruayruay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mid.ruayruayruay.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // แสดงผลหน้า login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.authenticate(username, password)) {
            return "redirect:/dashboard"; // ไปยังหน้า dashboard หากสำเร็จ
        }
        model.addAttribute("error", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // แสดงผลหน้า dashboard.html
    }
}
