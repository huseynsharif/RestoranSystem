package org.example.business.absracts;

import org.example.core.results.*;
import org.example.dataAccess.asbtracts.IUserDao;
import org.example.entities.User;
import org.example.entities.dto.UserLoginRequest;
import org.example.entities.dto.UserRegisterRequest;

import java.sql.SQLException;

public abstract class UserService {

    protected final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public Result prosesRegister() throws SQLException, ClassNotFoundException {

        UserRegisterRequest user = getRegisterFields();

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

    public DataResult<User> processLogin(){
        UserLoginRequest userLoginRequest = getLoginFields();

        User user = this.userDao.findByEmail(userLoginRequest.getEmail());

        return user.getPassword().equals(userLoginRequest.getPassword()) ?
                new SuccessDataResult<>(user, "Logged in succesfully") :
                new ErrorDataResult<User>("Email or password is incorrect");

    }

    protected abstract UserLoginRequest getLoginFields();

    public abstract UserRegisterRequest getRegisterFields();

}
