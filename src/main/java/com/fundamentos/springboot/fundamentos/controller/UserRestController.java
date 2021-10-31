package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentos.springboot.fundamentos.caseuse.DeletedUser;
import com.fundamentos.springboot.fundamentos.caseuse.GetUser;
import com.fundamentos.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private GetUser getUser;
    private CreateUser createUser;
    private DeletedUser deletedUser;
    private UpdateUser updateUser;
    private UserRepository userRepository;

    public UserRestController(GetUser getUser, CreateUser createUser,DeletedUser deletedUser,UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser= createUser;
        this.deletedUser=deletedUser;
        this.updateUser=updateUser;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    List<User> get(){
       return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User user){
        return  new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deletedUser.remove(id);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User user,@PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(user,id),HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size){
        return  userRepository.findAll(PageRequest.of(page,size)).getContent()
                ;
    }
}
