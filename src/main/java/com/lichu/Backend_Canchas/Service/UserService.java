package com.lichu.Backend_Canchas.Service;

import com.lichu.Backend_Canchas.Auth.AuthResponse;
import com.lichu.Backend_Canchas.Auth.LoginRequest;
import com.lichu.Backend_Canchas.Jwt.JwtService;
import com.lichu.Backend_Canchas.Model.User;
import com.lichu.Backend_Canchas.Repository.IUserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyección del PasswordEncoder

    @Autowired
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(User usuario) {
        // System.out.println("Contraseña antes de encriptar: " +
        // usuario.getPassword());
        // Encriptar la contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Guardar el usuario
        userRepo.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();

    }

    @Override
    public AuthResponse login(User user) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        UserDetails userDetails = userRepo.findByEmail(user.getEmail()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();

    }
}
