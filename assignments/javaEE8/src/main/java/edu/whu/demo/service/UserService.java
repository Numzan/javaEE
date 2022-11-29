package edu.whu.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Cacheable(cacheNames = "user",key = "#name",condition = "#name!=null")
    public User getUser(String name){
        Optional<User> user = userRepository.findById(name);
        return user.isEmpty()?null:user.get();
    }

    @CacheEvict(cacheNames = "user",key = "#name")
    public void deleteUser(String name) {
        userRepository.deleteById(name);
    }

    @CacheEvict(cacheNames = "user", key = "#name")
    public User updateUser(String name,User user){
        if(name==null ||!name.equals(user.getName())||userRepository.findById(name).isEmpty()){
            throw new RuntimeException("update error");
        }
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
}
