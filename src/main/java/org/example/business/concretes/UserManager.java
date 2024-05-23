package org.example.business.concretes;

import org.example.business.absracts.UserService;
import org.example.core.results.DataResult;
import org.example.core.results.Result;
import org.example.entities.dto.UserLoginRequest;
import org.example.entities.dto.UserRegisterRequest;

public class UserManager implements UserService {
    @Override
    public Result register(UserRegisterRequest user) {
        

        return null;
    }

    @Override
    public DataResult<String> login(UserLoginRequest user) {
        return null;
    }
}
