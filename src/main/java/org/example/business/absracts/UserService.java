package org.example.business.absracts;

import org.example.core.results.DataResult;
import org.example.core.results.Result;
import org.example.entities.User;
import org.example.entities.dto.UserLoginRequest;
import org.example.entities.dto.UserRegisterRequest;

public interface UserService {

    Result register(UserRegisterRequest user);
    DataResult<String> login(UserLoginRequest user);

}
