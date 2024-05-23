package org.example.business.absracts;

import org.example.core.results.Result;
import org.example.core.results.SuccessResult;
import org.example.dataAccess.asbtracts.IUserDao;
import org.example.entities.User;
import org.example.entities.dto.UserRegisterRequest;

public abstract class UserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    Result prosesRegister(){

        UserRegisterRequest user = getFields();

        saveUser(user);

        return new SuccessResult("Saved successfully");
    }

    private void saveUser(UserRegisterRequest userRegisterRequest) {

        User user = new User(
                0,
                userRegisterRequest.getFullName(),
                userRegisterRequest.getEmail(),
                userRegisterRequest.getPassword(),
                userRegisterRequest.getRole()
        );

        this.userDao.add(user);
    }

    public abstract UserRegisterRequest getFields();

}
