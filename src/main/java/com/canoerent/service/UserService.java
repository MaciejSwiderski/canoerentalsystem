package com.canoerent.service;

import com.canoerent.dto.UserDTO;
import com.canoerent.dto.UserPropDTO;
import com.canoerent.model.Role;
import com.canoerent.model.User;
import com.canoerent.repository.RoleRepository;
import com.canoerent.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public List<User> getUser() {
        return userRepository.findAll();
    }


    public List<UserPropDTO> getUsers() {

        List<User> users = userRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<UserPropDTO>>() {
        }.getType();
        List<UserPropDTO> userPropDTOS = modelMapper.map(users, listType);

        return userPropDTOS;
    }


    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    public UserDTO getUserById(Long id) {

        Optional<User> user = userRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);

        return userDTO;

    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserByUserEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
