package com.jcut.service;

import com.jcut.common.ServerResponse;
import com.jcut.pojo.User;

public interface IUserService {
    public ServerResponse<User> login(String username, String password);

    public ServerResponse<String> register(User user);

    public ServerResponse<String> checkValid(String str, String type);

    public ServerResponse selectQuestion(String username);

    public ServerResponse<String> checkAnswer(String username, String question, String answer);

    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    public ServerResponse<User> updateInformation(User user, User newUser);

    public ServerResponse<User> getinformation(String username);

    public ServerResponse checkAdminRole(User user);

    public ServerResponse delete(String username);

}
