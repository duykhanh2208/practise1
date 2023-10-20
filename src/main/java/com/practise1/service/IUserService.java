package com.practise1.service;

import com.practise1.model.User;

import java.util.Optional;


public interface IUserService extends IService<User>  {
    Optional<User> findUserByAccount(String account, String password);
}
