package br.com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "Index";
    }

    @GetMapping("/users")
    public List<User> getListUsers() {
       return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        User user;

        try {
           user =  userRepository.findById(id).get();
        }catch (Exception e){
            System.out.println("Failed to find user");
            return null;
        }

        return user;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User user){
        User userFind;

        try {
            userFind = userRepository.getById(id);

            userFind.setName(user.getName());
            userFind.setEmail(user.getEmail());
            userFind.setPassword(user.getPassword());
            
        }catch (Exception e) {
            System.out.println("Failed to find user");
            return null;
        }
        
        return userRepository.save(userFind);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Failed to delete user");
        }
    }
}