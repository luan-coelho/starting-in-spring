package br.com.springboot.controller;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;
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
    public User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public void alterUser(@PathVariable(value = "id") Long id, @RequestBody User user){
        User userFind = userRepository.getById(id);


    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        userRepository.deleteById(id);
    }
}
