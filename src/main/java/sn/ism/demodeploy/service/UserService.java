package sn.ism.demodeploy.service;

import sn.ism.demodeploy.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User fetchUserById(Long id);
    List<User> findAllUsers();


}
