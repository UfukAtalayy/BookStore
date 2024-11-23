package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.DTO.UserDTO;
import com.example.onlinebookstore.Entity.User;
import com.example.onlinebookstore.Mapper.UserMapper;
import com.example.onlinebookstore.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService,UserMapper userMapper){
        this.userService=userService;
        this.userMapper=userMapper;
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOS);
    }

    // ID ile kullanıcı al
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        return user.map(value -> ResponseEntity.ok(userMapper.toDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Yeni kullanıcı oluştur
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(userMapper.toDTO(savedUser));
    }

    // Kullanıcıyı güncelle
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        Optional<User> updatedUser = userService.updateUser(id,userMapper.toEntity(userDTO));
        return updatedUser.map(user -> ResponseEntity.ok(userMapper.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Kullanıcıyı sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
