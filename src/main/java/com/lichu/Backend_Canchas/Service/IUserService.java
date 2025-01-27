package com.lichu.Backend_Canchas.Service;

import com.lichu.Backend_Canchas.Auth.AuthResponse;
import com.lichu.Backend_Canchas.Model.User;

public interface IUserService {
    public AuthResponse register(User usuario);

    public AuthResponse login(User usuario);
}
