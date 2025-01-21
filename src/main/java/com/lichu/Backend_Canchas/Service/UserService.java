package com.lichu.Backend_Canchas.Service;

import com.lichu.Backend_Canchas.Model.User;
import com.lichu.Backend_Canchas.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyección del PasswordEncoder

    @Override
    public void registerUser(User usuario) {
        System.out.println("Contraseña antes de encriptar: " + usuario.getPassword());
        // Encriptar la contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Guardar el usuario
        userRepo.save(usuario);
    }

    @Override
    public Boolean checkLogin(User usuario) {
        Optional<User> userOptional = userRepo.findByEmail(usuario.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return passwordEncoder.matches(usuario.getPassword(), user.getPassword());
        }
        return false;
    }
}
