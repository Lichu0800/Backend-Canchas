package com.lichu.Backend_Canchas.Service;

import com.lichu.Backend_Canchas.Model.User;

public interface IUserService {
    public void registerUser(User usuario);

    public Boolean checkLogin(User usuario);
}
