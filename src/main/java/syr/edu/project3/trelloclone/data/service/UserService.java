package syr.edu.project3.trelloclone.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syr.edu.project3.trelloclone.data.models.User;
import syr.edu.project3.trelloclone.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

//UserService for implementing functions for user based api calls from UserController
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(String name){
        List<User> users = new ArrayList<User>();

        if (name == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByName(name).forEach(users::add);
        return users;
    }

    public User getUserById(long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found user with id = " + id));
    }

    public User updateUser(long id, User user) throws Exception {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Not found user with id = " + id));

        _user.setName(user.getName());
        return userRepository.save(_user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
