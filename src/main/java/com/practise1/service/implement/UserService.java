package com.practise1.service.implement;


import com.practise1.model.User;
import com.practise1.repository.UserRepository;
import com.practise1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findById(id).get());

    }
    @Override
    public Optional<User> findUserByAccount(String account, String password){
        Optional<User> user = userRepository.searchUser(account,password);
        return user;
    }

}
