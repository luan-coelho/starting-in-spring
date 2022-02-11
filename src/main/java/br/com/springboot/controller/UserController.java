package br.com.springboot.controller;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "Página Oficial";
    }

    @GetMapping("/users-list")
    public List<User> getListUsers() {
       return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return userRepository.findById(id).get();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User user){
        User userFind = userRepository.getById(id);

        try {
            if(userFind != null){
                userFind.setName(user.getName());
                userFind.setEmail(user.getEmail());
                userFind.setPassword(user.getPassword());

                return userRepository.save(userFind);
            }
        }catch (Exception e) {
            System.out.println("Failed to find user");
        }
        return null;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        userRepository.deleteById(id);
    }
}
