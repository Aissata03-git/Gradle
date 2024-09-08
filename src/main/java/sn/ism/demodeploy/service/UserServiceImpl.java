package sn.ism.demodeploy.service;

import sn.ism.demodeploy.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

public class UserServiceImpl implements UserService{
    List<User> users = new ArrayList<>(
            List.of(new User(1L,"John","john@dow.com","1i2s3m"),
                    new User(2L,"Jane","jane@dow.com","1i2s3m"),
                    new User(3L,"Joe","joe@dow.com","1i2s3m"))
    );
    @Override
    public User saveUser(User user) {
        user.setId(RandomGenerator.getDefault().nextLong());
        /*user.setName("jacques " + user.getName));*/
        users.add(user);
        return user;
    }

    @Override
    public User fetchUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(()-> new RuntimeException("user not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
