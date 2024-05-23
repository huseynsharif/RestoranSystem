package org.example.business.absracts;

import org.example.core.results.Result;
import org.example.core.results.SuccessResult;
import org.example.dataAccess.asbtracts.IUserDao;
import org.example.entities.User;
import org.example.entities.dto.UserRegisterRequest;

import java.sql.SQLException;

public abstract class UserService {

    protected final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public Result prosesRegister() throws SQLException, ClassNotFoundException {

        UserRegisterRequest user = getFields();

        saveUser(user);

        return new SuccessResult("Saved successfully");
    }

    private void saveUser(UserRegisterRequest userRegisterRequest) throws SQLException, ClassNotFoundException {

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
