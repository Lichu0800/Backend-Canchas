package com.lichu.Backend_Canchas.Controller;

import com.lichu.Backend_Canchas.Model.User;
import com.lichu.Backend_Canchas.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userServ;

    @CrossOrigin(origins = "http://localhost:3000") // Cambia a la URL de tu frontend
    @PostMapping("/register")
    public ResponseEntity<?> registeUser(@Valid @RequestBody User usuario) {
        return ResponseEntity.ok(userServ.register(usuario));

    }

    @CrossOrigin(origins = "http://localhost:3000") // Cambia a la URL de tu frontend
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody User usuario) {
        return ResponseEntity.ok(userServ.login(usuario));

    }

}
